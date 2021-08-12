package com.joaoandrade.objetivodiaapp.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.api.model.ObjetivoModel;
import com.joaoandrade.objetivodiaapp.domain.model.Objetivo;

@Component
public class ObjetivoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public ObjetivoModel toModel(Objetivo objetivo) {
		return modelMapper.map(objetivo, ObjetivoModel.class);
	}

}
