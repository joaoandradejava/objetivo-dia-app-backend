package com.joaoandrade.objetivodiaapp.domain.exception;

public class AnotacaoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public AnotacaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public AnotacaoNaoEncontradaException(Long id) {
		super(String.format("A Anotação de id %d não foi encontrada no sistema!", id));
	}
}
