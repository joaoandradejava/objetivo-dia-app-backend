package com.joaoandrade.objetivodiaapp.domain.model.enumeration;

public enum Perfil {
	CLIENTE("ROLE_CLIENTE"), ADMIN("ROLE_ADMIN");

	private String descricao;

	private Perfil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
