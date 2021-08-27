package com.joaoandrade.objetivodiaapp.api.model;

import java.time.LocalDateTime;

public class ObjetivoModel {
	private Long id;
	private String titulo;
	private LocalDateTime data;
	private int porcentagem;
	private int totalTarefas;
	private int totalTarefasConcluidas;
	private CategoriaModel categoria;

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

	public int getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(int porcentagem) {
		this.porcentagem = porcentagem;
	}

	public int getTotalTarefas() {
		return totalTarefas;
	}

	public void setTotalTarefas(int totalTarefas) {
		this.totalTarefas = totalTarefas;
	}

	public int getTotalTarefasConcluidas() {
		return totalTarefasConcluidas;
	}

	public void setTotalTarefasConcluidas(int totalTarefasConcluidas) {
		this.totalTarefasConcluidas = totalTarefasConcluidas;
	}

	public CategoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}

}
