package com.joaoandrade.objetivodiaapp.api.model;

import java.time.LocalDateTime;

public class ObjetivoModel {
	private Long id;
	private String titulo;
	private LocalDateTime data;

	public ObjetivoModel() {
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

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

}
