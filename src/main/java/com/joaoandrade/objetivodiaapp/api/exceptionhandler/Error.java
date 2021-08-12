package com.joaoandrade.objetivodiaapp.api.exceptionhandler;

public enum Error {
	NEGOCIO_EXCEPTION("erro-client-side", "Erro do lado do client-side(frontend)"),
	ENTIDADE_NAO_ENCONTRADA_EXCEPTION("entidade-nao-encontrada", "Entidade não encontrada"),
	ERRO_DE_VALIDACAO("erro-de-validacao", "Erro de validação"),
	ERRO_NA_DESSERIALIZACAO_JSON("erro-na-desserializacao-json", "Erro na desserialização do JSON"),
	PROPRIEDADE_INEXISTENTE("propriedade-inexistente", "Propriedade inexistente"),
	ENDPOINT_NAO_ENCONTRADO("endpoint-nao-encontrado", "Endpoint não encontrado"),
	METODO_NAO_SUPORTADO("metodo-nao-suportado", "Metodo não suportado"),
	ERRO_INTERNO_NO_SERVIDOR("erro-interno-no-servidor", "Erro interno no servidor");

	private String type;
	private String title;

	private Error(String type, String title) {
		this.type = "https://www.joaoandradejava.com.br/" + type;
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}
}
