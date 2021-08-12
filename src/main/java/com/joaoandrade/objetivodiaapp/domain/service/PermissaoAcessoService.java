package com.joaoandrade.objetivodiaapp.domain.service;

import org.springframework.stereotype.Service;

import com.joaoandrade.objetivodiaapp.core.security.UsuarioLogado;
import com.joaoandrade.objetivodiaapp.domain.exception.AcessoNegadoException;

@Service
public class PermissaoAcessoService {

	public void verificarSeTemPermissao(Long usuarioId, UsuarioLogado usuarioLogado, String mensagem) {
		if (usuarioLogado.getId() != usuarioId && !usuarioLogado.isAdmin()) {
			throw new AcessoNegadoException(mensagem);
		}
	}
}
