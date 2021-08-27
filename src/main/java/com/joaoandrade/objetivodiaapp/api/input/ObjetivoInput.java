package com.joaoandrade.objetivodiaapp.api.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ObjetivoInput {

	@Size(max = 255)
	@NotBlank
	private String titulo;

	@Valid
	@NotNull
	private CategoriaIdInput categoria;

	public ObjetivoInput() {
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public CategoriaIdInput getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaIdInput categoria) {
		this.categoria = categoria;
	}

}
