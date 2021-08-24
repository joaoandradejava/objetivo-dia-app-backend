package com.joaoandrade.objetivodiaapp.api.model;

import java.time.LocalDateTime;

public class AnotacaoModel {
	private Long id;
	private String titulo;
	private String conteudoResumo;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataModificacao;

	public AnotacaoModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConteudoResumo() {
		return conteudoResumo;
	}

	public void setConteudoResumo(String conteudoResumo) {
		this.conteudoResumo = conteudoResumo;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(LocalDateTime dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

}
