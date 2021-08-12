package com.joaoandrade.objetivodiaapp.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private Boolean isFeita = Boolean.FALSE;

	@ManyToOne
	@JoinColumn(name = "objetivo_id")
	private Objetivo objetivo;

	public Tarefa() {
	}

	public Tarefa(Long id, String titulo, Boolean isFeita, Objetivo objetivo) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.isFeita = isFeita;
		this.objetivo = objetivo;
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

	public Objetivo getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}

	public void fazerTarefa() {
		this.isFeita = true;
	}

	public void desfazerTarefa() {
		this.isFeita = false;
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
		Tarefa other = (Tarefa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
