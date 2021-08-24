package com.joaoandrade.objetivodiaapp.api.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AnotacaoInput {

	@Size(max = 255)
	@NotBlank
	private String titulo;
	private String conteudo;

	public AnotacaoInput() {
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

}
