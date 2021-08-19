package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;

import br.com.alura.forum.model.Resposta;
import lombok.Getter;

@Getter
public class RespostaDTO {
	
	private Long id;
	private String nomeAutor;
	private String mensagem;
	private LocalDateTime dataCriacao;
	
	public RespostaDTO(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.dataCriacao = resposta.getDataCriacao();
		this.nomeAutor = resposta.getAutor().getNome();
	}

}
