package com.joaoandrade.objetivodiaapp.core.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Autowired
	private JwtConfigProperty jwtConfigProperty;

	public String gerarTokenJwt(String email) {
		return Jwts.builder().setSubject(email)
				.setExpiration(new Date(System.currentTimeMillis() + jwtConfigProperty.getTempoDeExpiracaoDoTokenJwt()))
				.signWith(SignatureAlgorithm.HS512, jwtConfigProperty.getSenhaDoTokenJwt().getBytes()).compact();
	}

	public String getEmailDoUsuarioLogado(String tokenJwt) {
		Claims claims = getClaims(tokenJwt);

		return claims != null ? claims.getSubject() : null;
	}

	public boolean isTokenJwtValido(String tokenJwt) {
		Claims claims = getClaims(tokenJwt);

		if (claims != null) {
			String email = claims.getSubject();
			Date agora = new Date();
			Date dataDeExpiracaoDoTokenJwt = claims.getExpiration();

			if (StringUtils.hasLength(email) && dataDeExpiracaoDoTokenJwt != null
					&& agora.before(dataDeExpiracaoDoTokenJwt)) {
				return true;
			}
		}

		return false;
	}

	private Claims getClaims(String tokenJwt) {
		try {
			return Jwts.parser().setSigningKey(jwtConfigProperty.getSenhaDoTokenJwt().getBytes())
					.parseClaimsJws(tokenJwt).getBody();
		} catch (Exception e) {
			return null;
		}
	}
}
