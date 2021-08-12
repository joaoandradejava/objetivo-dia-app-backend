package com.joaoandrade.objetivodiaapp.api.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MudancaSenhaInput {

	@Size(max = 255)
	@NotBlank
	private String senhaAtual;

	@Size(max = 255)
	@NotBlank
	private String novaSenha;

	@Size(max = 255)
	@NotBlank
	private String confirmacaoSenha;

	public MudancaSenhaInput() {
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

}
