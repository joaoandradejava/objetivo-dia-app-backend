package com.joaoandrade.objetivodiaapp.api.model;

public class TarefaModel {
	private Long id;
	private String titulo;
	private Boolean isFeita;

	public TarefaModel() {
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

	public Boolean getIsFeita() {
		return isFeita;
	}

	public void setIsFeita(Boolean isFeita) {
		this.isFeita = isFeita;
	}

}
