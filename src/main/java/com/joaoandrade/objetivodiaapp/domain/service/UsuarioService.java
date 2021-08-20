package com.joaoandrade.objetivodiaapp.domain.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandrade.objetivodiaapp.domain.dto.GraficoObjetivoConcluidoDTO;
import com.joaoandrade.objetivodiaapp.domain.exception.NegocioException;
import com.joaoandrade.objetivodiaapp.domain.model.Objetivo;
import com.joaoandrade.objetivodiaapp.domain.model.Usuario;
import com.joaoandrade.objetivodiaapp.domain.service.crud.CrudObjetivoService;
import com.joaoandrade.objetivodiaapp.domain.service.crud.CrudUsuarioService;

@Service
public class UsuarioService {

	@Autowired
	private CrudUsuarioService crudUsuarioService;

	@Autowired
	private CrudObjetivoService crudObjetivoService;

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

	@Transactional
	public String esqueciASenha(String email) {
		Usuario usuario = crudUsuarioService.buscarPorEmail(email);
		String novaSenha = "alcanceseusobjetivos";
		char[] letras = { 'A', 'B', 'C', 'D', 'X', 'x', 'z', 's', 'a', 'm' };
		Random random = new Random();

		for (int i = 0; i < letras.length; i++) {
			novaSenha += letras[random.nextInt(letras.length - 1)];
		}

		usuario.setSenha(new BCryptPasswordEncoder().encode(novaSenha));

		return novaSenha;
	}

	public GraficoObjetivoConcluidoDTO obterGraficoDosObjetivosConcluidos(Long id) {
		List<Objetivo> objetivos = crudObjetivoService.buscarObjetivos(id);
		int objetivosConcluidos = 0;
		int objetivosNaoConcluidos = 0;

		for (Objetivo objetivo : objetivos) {
			if (objetivo.isConcluido()) {
				objetivosConcluidos++;
			} else {
				objetivosNaoConcluidos++;
			}
		}

		return new GraficoObjetivoConcluidoDTO(objetivosConcluidos, objetivosNaoConcluidos);
	}
}
