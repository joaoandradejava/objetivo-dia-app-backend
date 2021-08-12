package com.joaoandrade.objetivodiaapp.core.emailconfig;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.joaoandrade.objetivodiaapp.domain.service.email.EnvioEmailService;
import com.joaoandrade.objetivodiaapp.infrastructure.service.email.SendboxEnvioEmailService;
import com.joaoandrade.objetivodiaapp.infrastructure.service.email.SmtpEnvioEmailService;

@Configuration
public class EmailConfig {

	@Autowired
	private EmailConfigProperty emailConfigProperty;

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername(emailConfigProperty.getEmail());
		mailSender.setPassword("stkxfprqygzlfzhb");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

	@Bean
	public EnvioEmailService envioEmailService() {
		if (emailConfigProperty.getTipoEnvioEmail() != null
				&& emailConfigProperty.getTipoEnvioEmail() == TipoEnvioEmail.SENDBOX) {
			return new SendboxEnvioEmailService();
		}

		return new SmtpEnvioEmailService();
	}
}
