package com.joaoandrade.objetivodiaapp.domain.exception;

public class TarefaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public TarefaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public TarefaNaoEncontradaException(Long id) {
		super(String.format("A Tarefa de id %d não foi encontrada no sistema!", id));
	}
}
