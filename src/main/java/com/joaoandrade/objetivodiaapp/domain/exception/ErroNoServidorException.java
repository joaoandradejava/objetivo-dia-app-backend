package com.joaoandrade.objetivodiaapp.domain.exception;

public class ErroNoServidorException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public ErroNoServidorException(String mensagem) {
		super(mensagem);
	}

}
