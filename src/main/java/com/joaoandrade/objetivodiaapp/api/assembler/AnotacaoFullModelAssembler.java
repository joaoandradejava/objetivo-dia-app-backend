package com.joaoandrade.objetivodiaapp.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.api.model.AnotacaoFullModel;
import com.joaoandrade.objetivodiaapp.domain.model.Anotacao;

@Component
public class AnotacaoFullModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public AnotacaoFullModel toModel(Anotacao anotacao) {
		return modelMapper.map(anotacao, AnotacaoFullModel.class);
	}

}
