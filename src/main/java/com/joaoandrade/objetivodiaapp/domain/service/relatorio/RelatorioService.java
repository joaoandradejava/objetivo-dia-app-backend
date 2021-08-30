package com.joaoandrade.objetivodiaapp.domain.service.relatorio;

import java.util.Collection;
import java.util.Map;

public interface RelatorioService {

	public String gerarRelatorio(String nomeRelatorio, Map<String, Object> parametros, Collection<?> collection)
			throws Exception;
}
