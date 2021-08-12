package com.joaoandrade.objetivodiaapp.domain.exception;

public class ObjetivoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ObjetivoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ObjetivoNaoEncontradoException(Long id) {
		super(String.format("O Objetivo de id %d não foi encontrado no sistema!", id));
	}
}
