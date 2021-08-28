package com.joaoandrade.objetivodiaapp.domain.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.joaoandrade.objetivodiaapp.domain.exception.NegocioException;
import com.joaoandrade.objetivodiaapp.domain.exception.UsuarioNaoEncontradoException;
import com.joaoandrade.objetivodiaapp.domain.model.Usuario;
import com.joaoandrade.objetivodiaapp.domain.repository.UsuarioRepository;
import com.joaoandrade.objetivodiaapp.domain.service.validation.EmailUnicoValidacao;

@Service
public class CrudUsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private EmailUnicoValidacao emailUnicoValidacao;

	public Page<Usuario> buscarTodos(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Page<Usuario> buscarPorNome(String nome, Pageable pageable) {
		return repository.buscarPorNome(nome, pageable);
	}

	public Page<Usuario> buscarPorEmail(String email, Pageable pageable) {
		return repository.buscarPorEmail(email, pageable);
	}

	public Usuario buscarPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
	}

	public Usuario buscarPorEmail(String email) {
		return repository.findByEmail(email).orElseThrow(() -> new NegocioException(
				String.format("Não existe nenhum usuario com o email '%s' cadastrado no sistema!", email)));
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
