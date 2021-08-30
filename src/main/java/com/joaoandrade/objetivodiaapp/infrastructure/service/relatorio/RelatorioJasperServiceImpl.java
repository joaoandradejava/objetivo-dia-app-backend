package com.joaoandrade.objetivodiaapp.infrastructure.service.relatorio;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.joaoandrade.objetivodiaapp.domain.service.relatorio.RelatorioService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Primary
@Service
public class RelatorioJasperServiceImpl implements RelatorioService {

	@Override
	public String gerarRelatorio(String nomeRelatorio, Map<String, Object> parametros, Collection<?> collection)
			throws Exception {

		String caminhoRelatorio = "src" + File.separator + "main" + File.separator + "resources" + File.separator
				+ "relatorios" + File.separator + nomeRelatorio + ".jasper";

		JRBeanCollectionDataSource collectionDataSource = new JRBeanCollectionDataSource(collection);
		JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, parametros, collectionDataSource);

		return "data:application/pdf;base64,"
				+ Base64.encodeBase64String(JasperExportManager.exportReportToPdf(jasperPrint));
	}

}
