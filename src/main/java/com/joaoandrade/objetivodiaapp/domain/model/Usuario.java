package com.joaoandrade.objetivodiaapp.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.joaoandrade.objetivodiaapp.domain.model.enumeration.Perfil;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String senha;

	@CreationTimestamp
	private LocalDateTime dataCriacaoConta;

	@CollectionTable(name = "perfil")
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<Perfil> perfis = new HashSet<>();

	@OneToMany(mappedBy = "usuario", orphanRemoval = true)
	private List<Objetivo> objetivos = new ArrayList<>();

	@OneToMany(mappedBy = "usuario", orphanRemoval = true)
	private List<Anotacao> anotacoes = new ArrayList<>();

	public Usuario() {
		adicionarPerfil(Perfil.CLIENTE);
	}

	public Usuario(Long id, String nome, String email, String senha, LocalDateTime dataCriacaoConta) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.dataCriacaoConta = dataCriacaoConta;
		adicionarPerfil(Perfil.CLIENTE);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDateTime getDataCriacaoConta() {
		return dataCriacaoConta;
	}

	public void setDataCriacaoConta(LocalDateTime dataCriacaoConta) {
		this.dataCriacaoConta = dataCriacaoConta;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}

	public List<Objetivo> getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(List<Objetivo> objetivos) {
		this.objetivos = objetivos;
	}

	public void adicionarPerfil(Perfil perfil) {
		this.perfis.add(perfil);
	}

	public List<Anotacao> getAnotacoes() {
		return anotacoes;
	}

	public void setAnotacoes(List<Anotacao> anotacoes) {
		this.anotacoes = anotacoes;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
