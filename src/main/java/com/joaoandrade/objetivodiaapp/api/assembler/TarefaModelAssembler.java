package com.joaoandrade.objetivodiaapp.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.api.model.TarefaModel;
import com.joaoandrade.objetivodiaapp.domain.model.Tarefa;

@Component
public class TarefaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public TarefaModel toModel(Tarefa tarefa) {
		return modelMapper.map(tarefa, TarefaModel.class);
	}

}
