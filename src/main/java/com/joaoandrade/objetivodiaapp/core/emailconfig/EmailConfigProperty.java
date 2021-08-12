package com.joaoandrade.objetivodiaapp.core.emailconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("email")
public class EmailConfigProperty {

	private String email;
	private String sendboxEmail;
	private TipoEnvioEmail tipoEnvioEmail;

	public EmailConfigProperty() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSendboxEmail() {
		return sendboxEmail;
	}

	public void setSendboxEmail(String sendboxEmail) {
		this.sendboxEmail = sendboxEmail;
	}

	public TipoEnvioEmail getTipoEnvioEmail() {
		return tipoEnvioEmail;
	}

	public void setTipoEnvioEmail(TipoEnvioEmail tipoEnvioEmail) {
		this.tipoEnvioEmail = tipoEnvioEmail;
	}

}
