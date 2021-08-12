package com.joaoandrade.objetivodiaapp.api.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ObjetivoInput {

	@Size(max = 255)
	@NotBlank
	private String titulo;

	public ObjetivoInput() {
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
