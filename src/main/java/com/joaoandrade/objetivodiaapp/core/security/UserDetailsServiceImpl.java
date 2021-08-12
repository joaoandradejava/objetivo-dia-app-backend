package com.joaoandrade.objetivodiaapp.core.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.joaoandrade.objetivodiaapp.domain.model.Usuario;
import com.joaoandrade.objetivodiaapp.domain.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> obj = repository.findByEmail(username);

		if (obj.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}

		Usuario usuario = obj.get();
		return new UsuarioLogado(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(),
				usuario.getPerfis());
	}

}
