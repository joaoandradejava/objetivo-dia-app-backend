package com.joaoandrade.objetivodiaapp.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.api.model.ObjetivoFullModel;
import com.joaoandrade.objetivodiaapp.domain.model.Objetivo;

@Component
public class ObjetivoFullModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public ObjetivoFullModel toModel(Objetivo objetivo) {
		ObjetivoFullModel objetivoFullModel = modelMapper.map(objetivo, ObjetivoFullModel.class);
		objetivoFullModel.setPorcentagem(objetivo.porcetagem());

		return objetivoFullModel;
	}

}
