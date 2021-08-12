package com.joaoandrade.objetivodiaapp.infrastructure.service.email;

import javax.mail.internet.MimeMessage;

import com.joaoandrade.objetivodiaapp.domain.model.Usuario;

public class SmtpEnvioEmailService extends AbstractEnvioEmailService {

	@Override
	public void enviarEmail(Usuario usuario, String titulo, String corpo) {
		MimeMessage mimeMessage = prepararMimeMessage(usuario, titulo, corpo);
		javaMailSender.send(mimeMessage);
	}

}
