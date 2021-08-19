package br.com.alura.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.DetalhesDoTopicoDTO;
import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping("/topicos/lista")
	public List<TopicoDTO> lista (){
		List<Topico> topicos = topicoRepository.findAll();
		return TopicoDTO.converter(topicos);
	}
	
	
	@GetMapping("/topicos/listaCurso/{curso}")
	public List<TopicoDTO> lista (@PathVariable String curso){
		List<Topico> topicos = topicoRepository.findByCursoNome(curso);
		return TopicoDTO.converter(topicos);
	}


	@PostMapping("/topicos/cadastra")
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder) { 
		Topico topico = topicoForm.converter(cursoRepository);
		topicoRepository.save(topico); 
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}
	
	@GetMapping("/topicos/detalha/{id}")
	public ResponseEntity <DetalhesDoTopicoDTO> detalhar (@PathVariable Long id) { 
	    Optional<Topico> topico = topicoRepository.findById(id);
	    if(topico.isPresent()){
	        return ResponseEntity.ok(new DetalhesDoTopicoDTO(topico.get()));	
	    }
	    return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/topicos/atualiza/{id}")
	@Transactional 
	public ResponseEntity<TopicoDTO> atualizar (@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm atualizacaoTopicoForm){
	    Optional<Topico> optional = topicoRepository.findById(id);
	    if(optional.isPresent()){
	        Topico topico = atualizacaoTopicoForm.edita(id, topicoRepository);
	        return ResponseEntity.ok(new TopicoDTO(topico));	
	    }
	    return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/topicos/remove/{id}")
	@Transactional 
	public ResponseEntity<?> remover (@PathVariable Long id){
	    Optional<Topico> optional = topicoRepository.findById(id);
	    if(optional.isPresent()){
	        topicoRepository.deleteById(id);
	        return ResponseEntity.ok().build();	
	    }
	    return ResponseEntity.notFound().build();								
	}

	
}
