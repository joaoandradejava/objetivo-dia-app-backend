package com.joaoandrade.objetivodiaapp.domain.service.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandrade.objetivodiaapp.domain.exception.NegocioException;
import com.joaoandrade.objetivodiaapp.domain.exception.ObjetivoNaoEncontradoException;
import com.joaoandrade.objetivodiaapp.domain.exception.TarefaNaoEncontradaException;
import com.joaoandrade.objetivodiaapp.domain.model.Objetivo;
import com.joaoandrade.objetivodiaapp.domain.model.Tarefa;
import com.joaoandrade.objetivodiaapp.domain.repository.ObjetivoRepository;
import com.joaoandrade.objetivodiaapp.domain.repository.TarefaRepository;

@Service
public class CrudTarefaService {

	@Autowired
	private TarefaRepository repository;

	@Autowired
	private ObjetivoRepository objetivoRepository;

	public Objetivo buscarObjetivoPorId(Long objetivoId) {
		return objetivoRepository.findById(objetivoId)
				.orElseThrow(() -> new ObjetivoNaoEncontradoException(objetivoId));

	}

	public List<Tarefa> buscarTodasTarefasDoObjetivo(Long objetivoId) {
		buscarObjetivoPorId(objetivoId);

		return repository.buscarTodasTarefasDoObjetivo(objetivoId);
	}

	public Tarefa buscarTarefaPorId(Long objetivoId, Long tarefaId) {
		buscarObjetivoPorId(objetivoId);
		repository.findById(tarefaId).orElseThrow(() -> new TarefaNaoEncontradaException(tarefaId));

		return repository.buscarTarefaDoObjetivo(objetivoId, tarefaId).orElseThrow(() -> new NegocioException(
				String.format("A Tarefa de id %d não estar associada com o Objetivo de id %d", tarefaId, objetivoId)));
	}

	@Transactional
	public Tarefa salvar(Tarefa tarefa, Long objetivoId) {
		tarefa.setObjetivo(buscarObjetivoPorId(objetivoId));

		return repository.save(tarefa);
	}

	@Transactional
	public void deletarTarefaPorId(Long objetivoId, Long tarefaId) {
		buscarTarefaPorId(objetivoId, tarefaId);

		repository.deleteById(tarefaId);
	}

}
