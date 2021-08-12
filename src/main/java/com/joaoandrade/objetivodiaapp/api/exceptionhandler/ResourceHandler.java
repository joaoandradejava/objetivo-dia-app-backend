package com.joaoandrade.objetivodiaapp.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.joaoandrade.objetivodiaapp.domain.exception.AcessoNegadoException;
import com.joaoandrade.objetivodiaapp.domain.exception.EntidadeNaoEncontradaException;
import com.joaoandrade.objetivodiaapp.domain.exception.ErroNoServidorException;
import com.joaoandrade.objetivodiaapp.domain.exception.NegocioException;

@ControllerAdvice
public class ResourceHandler extends ResponseEntityExceptionHandler {

	private static final String DEFAULT_MESSAGE = "Ocorreu um erro inesperado. Se o problema persistir recomendo que entre em contato com o desenvolvedor da Api.";

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleErroInternoNoServidor(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Error error = Error.ERRO_INTERNO_NO_SERVIDOR;
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(),
				DEFAULT_MESSAGE, DEFAULT_MESSAGE);

		return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(ErroNoServidorException.class)
	public ResponseEntity<Object> handleErroNoServidor(ErroNoServidorException ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Error error = Error.ERRO_INTERNO_NO_SERVIDOR;
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(),
				ex.getMessage(), DEFAULT_MESSAGE);

		return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(AcessoNegadoException.class)
	public ResponseEntity<Object> handleAcessoNegado(AcessoNegadoException ex, WebRequest request) {
		HttpStatus status = HttpStatus.FORBIDDEN;
		Error error = Error.ACESSO_NEGADO;
		String message = ex.getMessage();
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				message);

		return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Error error = Error.NEGOCIO_EXCEPTION;
		String message = ex.getMessage();
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				message);

		return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		Error error = Error.ENTIDADE_NAO_ENCONTRADA_EXCEPTION;
		String message = ex.getMessage();
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				message);

		return handleExceptionInternal(ex, problemDetail, new HttpHeaders(), status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Error error = Error.ERRO_DE_VALIDACAO;
		String message = "Ocorreu um erro de validação, verifique se os dados foram preenchidos corretamente.";
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				message);

		for (ObjectError objectError : ex.getAllErrors()) {
			String field = objectError.getObjectName();

			if (objectError instanceof FieldError) {
				field = ((FieldError) objectError).getField();
			}

			String userMessage = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

			problemDetail.adicionarError(field, userMessage);
		}

		return handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable cause = ex.getCause();

		if (cause instanceof UnrecognizedPropertyException) {
			return handleUnrecognizedProperty((UnrecognizedPropertyException) cause, headers, status, request);
		}

		Error error = Error.ERRO_NA_DESSERIALIZACAO_JSON;
		String message = "Ocorreu um erro na desserialização do JSON. Verifique se os dados foram inseridos corretamente.";
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				DEFAULT_MESSAGE);

		return handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Error error = Error.ENDPOINT_NAO_ENCONTRADO;
		String message = String.format(
				"A requisição feita para o recurso de endpoint '%s%s' não foi encontrada. Esse Endpoint é inexistente",
				ex.getHttpMethod(), ex.getRequestURL());
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				DEFAULT_MESSAGE);

		return handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Error error = Error.METODO_NAO_SUPORTADO;
		String message = String.format("A requisiçao feita para o metodo '%s' não foi suportada", ex.getMethod());
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				DEFAULT_MESSAGE);

		return handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	private ResponseEntity<Object> handleUnrecognizedProperty(UnrecognizedPropertyException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Error error = Error.PROPRIEDADE_INEXISTENTE;
		String message = String.format("A Propriedade '%s' é inexistente.", ex.getPropertyName());
		ProblemDetail problemDetail = new ProblemDetail(error.getType(), error.getTitle(), status.value(), message,
				DEFAULT_MESSAGE);

		return handleExceptionInternal(ex, problemDetail, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (body == null) {
			body = new ProblemDetail(null, null, status.value(), status.getReasonPhrase(), DEFAULT_MESSAGE);
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

}
