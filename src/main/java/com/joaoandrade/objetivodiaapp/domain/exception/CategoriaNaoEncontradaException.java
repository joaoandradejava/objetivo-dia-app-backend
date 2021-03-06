package com.joaoandrade.objetivodiaapp.domain.exception;

public class CategoriaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CategoriaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public CategoriaNaoEncontradaException(Long id) {
		super(String.format("A Categoria de id %d não foi encontrada no sistema!", id));
	}
}
