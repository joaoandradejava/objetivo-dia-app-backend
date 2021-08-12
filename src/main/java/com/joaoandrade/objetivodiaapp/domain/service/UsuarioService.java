package com.joaoandrade.objetivodiaapp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandrade.objetivodiaapp.domain.exception.NegocioException;
import com.joaoandrade.objetivodiaapp.domain.model.Usuario;
import com.joaoandrade.objetivodiaapp.domain.service.crud.CrudUsuarioService;

@Service
public class UsuarioService {

	@Autowired
	private CrudUsuarioService crudUsuarioService;

	@Transactional
	public void mudarSenha(Long id, String senhaAtual, String novaSenha, String confirmacaoSenha) {
		Usuario usuario = crudUsuarioService.buscarPorId(id);
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		if (!novaSenha.equals(confirmacaoSenha)) {
			throw new NegocioException("As senhas não correspondem");
		}

		if (!bCryptPasswordEncoder.matches(senhaAtual, usuario.getSenha())) {
			throw new NegocioException("A Senha atual informada não corresponde a sua senha!");
		}

		usuario.setSenha(bCryptPasswordEncoder.encode(novaSenha));
	}
}
