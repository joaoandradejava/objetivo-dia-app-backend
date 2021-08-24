package com.joaoandrade.objetivodiaapp.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandrade.objetivodiaapp.domain.model.Anotacao;

@Repository
public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {

	@Query("select a from Anotacao a where a.usuario.id = ?1")
	@Transactional(readOnly = true)
	Page<Anotacao> buscarAnotacoesDoUsuario(Long usuarioId, Pageable pageable);

	@Query("select a from Anotacao a where a.usuario.id = ?1 and lower(a.titulo) like lower(concat('%',?2,'%'))")
	@Transactional(readOnly = true)
	Page<Anotacao> buscarAnotacoesDoUsuarioPeloTitulo(Long usuarioId, String titulo, Pageable pageable);

	@Query("select a from Anotacao a where a.usuario.id = ?1 and a.id = ?2")
	Optional<Anotacao> buscarAnotacaoDoUsuario(Long usuarioId, Long anotacaoId);
}
