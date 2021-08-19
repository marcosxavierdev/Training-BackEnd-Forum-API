package br.com.alura.forum.controller.config.validation;

import lombok.Getter;

@Getter
public class ErroFormDTO {

	private String campo;
	private String erro;
	
	public ErroFormDTO(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}
	
}
