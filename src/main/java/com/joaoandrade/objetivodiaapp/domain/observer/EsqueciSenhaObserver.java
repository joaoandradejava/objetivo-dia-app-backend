package com.joaoandrade.objetivodiaapp.domain.observer;

import com.joaoandrade.objetivodiaapp.domain.model.Usuario;

public class EsqueciSenhaObserver {

	private final Usuario usuario;
	private final String novaSenha;

	public EsqueciSenhaObserver(Usuario usuario, String novaSenha) {
		this.usuario = usuario;
		this.novaSenha = novaSenha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

}
