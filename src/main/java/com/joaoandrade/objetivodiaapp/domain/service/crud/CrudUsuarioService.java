package com.joaoandrade.objetivodiaapp.domain.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.joaoandrade.objetivodiaapp.domain.exception.UsuarioNaoEncontradoException;
import com.joaoandrade.objetivodiaapp.domain.model.Usuario;
import com.joaoandrade.objetivodiaapp.domain.repository.UsuarioRepository;
import com.joaoandrade.objetivodiaapp.domain.validation.EmailUnicoValidacao;

@Service
public class CrudUsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private EmailUnicoValidacao emailUnicoValidacao;

	public Usuario buscarPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
	}

	@Transactional
	public Usuario salvar(Usuario usuario) {
		if (StringUtils.hasLength(usuario.getEmail())) {
			emailUnicoValidacao.validarEmailUnico(usuario);
		}

		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));

		return repository.save(usuario);
	}

	@Transactional
	public Usuario atualizar(Usuario usuario) {
		return repository.save(usuario);
	}

	@Transactional
	public void deletarPorId(Long id) {
		buscarPorId(id);

		repository.deleteById(id);
	}
}
