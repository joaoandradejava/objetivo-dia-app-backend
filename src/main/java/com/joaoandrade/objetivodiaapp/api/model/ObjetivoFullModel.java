package com.joaoandrade.objetivodiaapp.api.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ObjetivoFullModel {
	private Long id;
	private String titulo;
	private LocalDateTime data;
	private int porcentagem;
	private List<TarefaModel> tarefas = new ArrayList<>();

	public ObjetivoFullModel() {
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

	public List<TarefaModel> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<TarefaModel> tarefas) {
		this.tarefas = tarefas;
	}

}
