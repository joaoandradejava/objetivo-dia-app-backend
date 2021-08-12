package com.joaoandrade.objetivodiaapp.domain.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.domain.exception.NegocioException;
import com.joaoandrade.objetivodiaapp.domain.model.Usuario;
import com.joaoandrade.objetivodiaapp.domain.repository.UsuarioRepository;

@Component
public class EmailUnicoValidacao {

	@Autowired
	private UsuarioRepository repository;

	public void validarEmailUnico(Usuario usuario) {
		Optional<Usuario> obj = repository.findByEmail(usuario.getEmail());

		if (obj.isPresent() && !obj.get().equals(usuario)) {
			throw new NegocioException(
					String.format("Já existe um usuario com o email '%s' cadastrado no sistema!", usuario.getEmail()));
		}
	}
}
