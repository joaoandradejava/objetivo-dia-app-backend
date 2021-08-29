package com.joaoandrade.objetivodiaapp.api.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ArtigoInput {

	@Size(max = 255)
	@NotBlank
	private String titulo;

	@NotBlank
	private String avatarUrl;

	@Size(max = 255)
	@NotBlank
	private String autor;

	@Size(max = 255)
	@NotBlank
	private String descricao;

	@NotBlank
	private String conteudo;

	@Valid
	@NotNull
	private CategoriaIdInput categoria;

	public ArtigoInput() {
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public CategoriaIdInput getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaIdInput categoria) {
		this.categoria = categoria;
	}

}
