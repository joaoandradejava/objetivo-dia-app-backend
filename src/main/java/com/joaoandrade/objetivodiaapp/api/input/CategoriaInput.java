package com.joaoandrade.objetivodiaapp.api.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoriaInput {

	@Size(max = 255)
	@NotBlank
	private String nome;

	@NotBlank
	private String avatarUrl;

	@Size(max = 255)
	@NotBlank
	private String codigoCor;

	public CategoriaInput() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getCodigoCor() {
		return codigoCor;
	}

	public void setCodigoCor(String codigoCor) {
		this.codigoCor = codigoCor;
	}

}
