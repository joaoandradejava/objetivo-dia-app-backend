package com.joaoandrade.objetivodiaapp.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.api.input.TarefaInput;
import com.joaoandrade.objetivodiaapp.domain.model.Tarefa;

@Component
public class TarefaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Tarefa toDomainModel(TarefaInput tarefaInput) {
		return modelMapper.map(tarefaInput, Tarefa.class);
	}

	public void copyToDomainModel(TarefaInput tarefaInput, Tarefa tarefa) {
		modelMapper.map(tarefaInput, tarefa);
	}
}
