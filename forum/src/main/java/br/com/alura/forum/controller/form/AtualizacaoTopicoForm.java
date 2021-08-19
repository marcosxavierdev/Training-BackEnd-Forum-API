package br.com.alura.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AtualizacaoTopicoForm {
	
	@NotNull @NotEmpty @Length(min=5)
	private String titulo;
	@NotNull @NotEmpty @Length(min=10)
	private String mensagem;
	
	
	public Topico edita(Long id, TopicoRepository topicoRepository) {/*recebe o paramentro id e repository para fazer a busca*/
		Topico topico = topicoRepository.getOne(id);/*topico recebe a busca por id*/
		topico.setTitulo(this.titulo); /*faz a alteração*/
		topico.setMensagem(this.mensagem); /*faz a alteração*/
		return topico; /*retorna o topico alterado*/
	}


}
