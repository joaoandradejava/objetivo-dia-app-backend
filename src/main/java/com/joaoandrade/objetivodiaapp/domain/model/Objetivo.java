package com.joaoandrade.objetivodiaapp.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Objetivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;

	@CreationTimestamp
	private LocalDateTime data;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@OneToMany(mappedBy = "objetivo", orphanRemoval = true)
	private List<Tarefa> tarefas = new ArrayList<>();

	public Objetivo() {
	}

	public Objetivo(Long id, String titulo, LocalDateTime data, Categoria categoria, Usuario usuario) {
		this.id = id;
		this.titulo = titulo;
		this.data = data;
		this.categoria = categoria;
		this.usuario = usuario;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public int quantidadeTotalDeTarefa() {
		return this.tarefas.size();
	}

	public int quantidadeDeTarefasConcluidas() {
		int total = 0;

		for (Tarefa tarefa : this.tarefas) {
			if (tarefa.getIsFeita()) {
				total++;
			}
		}

		return total;
	}

	public int porcetagem() {
		if (tarefas.size() == 0) {
			return 0;
		}

		double porcentagem = 0.0;

		for (Tarefa tarefa : this.tarefas) {
			if (tarefa.getIsFeita()) {
				porcentagem++;
			}
		}

		return (int) ((porcentagem / tarefas.size()) * 100.0);
	}

	public boolean isConcluido() {
		if (tarefas.size() == 0) {
			return false;
		}

		for (Tarefa tarefa : tarefas) {
			if (tarefa.getIsFeita() == false) {
				return false;
			}
		}

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Objetivo other = (Objetivo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
