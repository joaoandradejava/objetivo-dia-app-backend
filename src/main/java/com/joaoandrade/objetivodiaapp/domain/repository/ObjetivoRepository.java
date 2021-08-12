package com.joaoandrade.objetivodiaapp.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joaoandrade.objetivodiaapp.domain.model.Objetivo;

@Repository
public interface ObjetivoRepository extends JpaRepository<Objetivo, Long> {

	@Query("select o from Objetivo o where o.usuario.id = ?1")
	Page<Objetivo> buscarObjetivos(Long usuarioId, Pageable pageable);

	@Query("select o from Objetivo o where o.usuario.id = ?1 and o.id = ?2")
	Optional<Objetivo> buscarObjetivoDoUsuario(Long usuarioId, Long objetivoId);
}
