package com.joaoandrade.objetivodiaapp.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joaoandrade.objetivodiaapp.domain.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	@Query("select t from Tarefa t where t.objetivo.id = ?1")
	List<Tarefa> buscarTodasTarefasDoObjetivo(Long objetivoId);

	@Query("select t from Tarefa t where t.objetivo.id = ?1 and t.id = ?2")
	Optional<Tarefa> buscarTarefaDoObjetivo(Long objetivoId, Long tarefaId);
}
