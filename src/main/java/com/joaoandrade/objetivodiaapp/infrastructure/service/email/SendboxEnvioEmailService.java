package com.joaoandrade.objetivodiaapp.infrastructure.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;

import com.joaoandrade.objetivodiaapp.domain.exception.ErroNoServidorException;
import com.joaoandrade.objetivodiaapp.domain.model.Usuario;

public class SendboxEnvioEmailService extends AbstractEnvioEmailService {

	@Override
	public void enviarEmail(Usuario usuario, String titulo, String corpo) {
		try {
			MimeMessage mimeMessage = prepararMimeMessage(usuario, titulo, corpo);
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
			helper.setTo(emailConfigProperty.getSendboxEmail());
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new ErroNoServidorException("Erro ao tentar preparar o Mime Message");
		}
	}

}
