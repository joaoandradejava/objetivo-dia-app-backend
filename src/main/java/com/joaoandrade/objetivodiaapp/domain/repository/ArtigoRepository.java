package com.joaoandrade.objetivodiaapp.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joaoandrade.objetivodiaapp.domain.model.Artigo;

@Repository
public interface ArtigoRepository extends JpaRepository<Artigo, Long> {

	@Query("select a from Artigo a where lower(a.titulo) = lower(?1)")
	Optional<Artigo> buscarPorTitulo(String titulo);
}
