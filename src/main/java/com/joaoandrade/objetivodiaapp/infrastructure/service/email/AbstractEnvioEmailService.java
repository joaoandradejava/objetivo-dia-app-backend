package com.joaoandrade.objetivodiaapp.infrastructure.service.email;

import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.joaoandrade.objetivodiaapp.core.emailconfig.EmailConfigProperty;
import com.joaoandrade.objetivodiaapp.domain.exception.ErroNoServidorException;
import com.joaoandrade.objetivodiaapp.domain.model.Usuario;
import com.joaoandrade.objetivodiaapp.domain.service.email.EnvioEmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;

public abstract class AbstractEnvioEmailService implements EnvioEmailService {

	@Autowired
	protected EmailConfigProperty emailConfigProperty;

	@Autowired
	protected JavaMailSender javaMailSender;

	@Autowired
	private Configuration configuration;

	protected MimeMessage prepararMimeMessage(Usuario usuario, String titulo, String corpo) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			helper.setSentDate(new Date());
			helper.setFrom(emailConfigProperty.getEmail());
			helper.setTo(usuario.getEmail());
			helper.setSubject(titulo);
			helper.setText(transformarHtmlEmString(usuario, corpo), true);

			return mimeMessage;
		} catch (Exception e) {
			throw new ErroNoServidorException("Erro ao tentar preparar o Mime Message");
		}
	}

	private String transformarHtmlEmString(Usuario usuario, String nomeDoHtml) throws Exception {
		Template template = configuration.getTemplate(nomeDoHtml);

		return FreeMarkerTemplateUtils.processTemplateIntoString(template, usuario);
	}
}
