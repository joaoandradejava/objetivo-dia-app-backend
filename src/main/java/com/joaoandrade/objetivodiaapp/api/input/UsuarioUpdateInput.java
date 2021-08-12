package com.joaoandrade.objetivodiaapp.api.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioUpdateInput {

	@Size(min = 3, max = 255)
	@NotBlank
	private String nome;

	public UsuarioUpdateInput() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
