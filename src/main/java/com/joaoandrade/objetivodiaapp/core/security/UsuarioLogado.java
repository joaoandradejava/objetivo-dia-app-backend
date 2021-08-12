package com.joaoandrade.objetivodiaapp.core.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.joaoandrade.objetivodiaapp.domain.model.enumeration.Perfil;

public class UsuarioLogado implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	private boolean isAdmin;

	public UsuarioLogado() {
	}

	public UsuarioLogado(Long id, String nome, String email, String senha, Set<Perfil> perfis) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.authorities = perfis.stream().map(perfil -> new SimpleGrantedAuthority(perfil.getDescricao()))
				.collect(Collectors.toList());
		this.isAdmin = perfis.contains(Perfil.ADMIN);
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
