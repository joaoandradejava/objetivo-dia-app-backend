package com.joaoandrade.objetivodiaapp.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joaoandrade.objetivodiaapp.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);

	@Query("select u from Usuario u where lower(u.nome) like lower(concat('%', ?1, '%'))")
	Page<Usuario> buscarPorNome(String nome, Pageable pageable);

	@Query("select u from Usuario u where lower(u.email) like  lower(concat('%', ?1, '%'))")
	Page<Usuario> buscarPorEmail(String email, Pageable pageable);

}
