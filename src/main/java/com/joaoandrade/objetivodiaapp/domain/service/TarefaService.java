package com.joaoandrade.objetivodiaapp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandrade.objetivodiaapp.domain.model.Tarefa;
import com.joaoandrade.objetivodiaapp.domain.service.crud.CrudTarefaService;

@Service
public class TarefaService {

	@Autowired
	private CrudTarefaService crudTarefaService;

	@Transactional
	public void fazerTarefa(Long objetivoId, Long tarefaId) {
		Tarefa tarefa = crudTarefaService.buscarTarefaPorId(objetivoId, tarefaId);

		tarefa.fazerTarefa();
	}

	@Transactional
	public void desfazerTarefa(Long objetivoId, Long tarefaId) {
		Tarefa tarefa = crudTarefaService.buscarTarefaPorId(objetivoId, tarefaId);

		tarefa.desfazerTarefa();
	}

}
