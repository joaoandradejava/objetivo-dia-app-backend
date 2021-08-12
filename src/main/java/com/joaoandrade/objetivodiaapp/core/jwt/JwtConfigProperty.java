package com.joaoandrade.objetivodiaapp.core.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("jwt")
public class JwtConfigProperty {

	/**
	 * Senha do token jwt
	 */
	private String senhaDoTokenJwt;

	/**
	 * Tempo de expiração do token jwt
	 */
	private long tempoDeExpiracaoDoTokenJwt;

	public JwtConfigProperty() {
	}

	public String getSenhaDoTokenJwt() {
		return senhaDoTokenJwt;
	}

	public void setSenhaDoTokenJwt(String senhaDoTokenJwt) {
		this.senhaDoTokenJwt = senhaDoTokenJwt;
	}

	public long getTempoDeExpiracaoDoTokenJwt() {
		return tempoDeExpiracaoDoTokenJwt;
	}

	public void setTempoDeExpiracaoDoTokenJwt(long tempoDeExpiracaoDoTokenJwt) {
		this.tempoDeExpiracaoDoTokenJwt = tempoDeExpiracaoDoTokenJwt;
	}

}
