package com.joaoandrade.objetivodiaapp.domain.exception;

public class ArtigoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ArtigoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ArtigoNaoEncontradoException(Long id) {
		super(String.format("O Artigo de id %d não foi encontrado no sistema!", id));
	}
}
