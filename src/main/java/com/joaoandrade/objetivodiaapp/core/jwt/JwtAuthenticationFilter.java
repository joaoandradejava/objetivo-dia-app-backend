package com.joaoandrade.objetivodiaapp.core.jwt;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaoandrade.objetivodiaapp.api.exceptionhandler.Error;
import com.joaoandrade.objetivodiaapp.core.security.LoginDTO;
import com.joaoandrade.objetivodiaapp.core.security.UsuarioLogado;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private JwtUtil jwtUtil;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		setAuthenticationManager(authenticationManager);
		setAuthenticationFailureHandler(new FailureHandler());
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			LoginDTO loginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					loginDTO.getEmail(), loginDTO.getSenha());

			return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		UsuarioLogado usuarioLogado = (UsuarioLogado) authResult.getPrincipal();

		String tokenJwt = "Bearer " + jwtUtil.gerarTokenJwt(usuarioLogado.getUsername());
		response.setHeader("Authorization", tokenJwt);
		response.getWriter().write(json(usuarioLogado, tokenJwt));
		response.setContentType("application/json");
	}

	private String json(UsuarioLogado usuarioLogado, String tokenJwt) {
		StringBuilder builder = new StringBuilder();
		builder.append("{\n");
		builder.append("\"id\": " + usuarioLogado.getId() + ",\n");
		builder.append("\"nome\": \"" + usuarioLogado.getNome() + "\",\n");
		builder.append("\"isAdmin\": " + usuarioLogado.isAdmin() + ",\n");
		builder.append("\"tokenJwt\": \"" + tokenJwt + "\"");
		builder.append("}");

		return builder.toString();
	}

	private class FailureHandler implements AuthenticationFailureHandler {

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {

			response.setStatus(401);
			response.setContentType("application/json");
			response.getWriter().write(json());
		}

		private String json() {
			StringBuilder builder = new StringBuilder();
			Error error = Error.CREDENCIAIS_INCORRETAS;
			String message = "Verifique se o o email ou a senha foram inseridos corretamente";

			builder.append("{\n");
			builder.append("\"timestamp\":\"" + LocalDateTime.now() + "\",\n");
			builder.append("\"type\":\"" + error.getType() + "\",\n");
			builder.append("\"title\":\"" + error.getTitle() + "\",\n");
			builder.append("\"status\":" + 401 + ",\n");
			builder.append("\"detail\":\"" + message + "\",\n");
			builder.append("\"userMessage\":\"" + message + "\"\n");
			builder.append("}");

			return builder.toString();
		}

	}

}
