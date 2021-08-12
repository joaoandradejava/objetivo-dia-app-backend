package com.joaoandrade.objetivodiaapp.api.model;

import java.time.LocalDateTime;

public class UsuarioModel {
	private Long id;
	private String nome;
	private String email;
	private LocalDateTime dataCriacaoConta;

	public UsuarioModel() {
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

	public LocalDateTime getDataCriacaoConta() {
		return dataCriacaoConta;
	}

	public void setDataCriacaoConta(LocalDateTime dataCriacaoConta) {
		this.dataCriacaoConta = dataCriacaoConta;
	}

}
