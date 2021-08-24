package com.joaoandrade.objetivodiaapp.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.api.input.AnotacaoInput;
import com.joaoandrade.objetivodiaapp.domain.model.Anotacao;

@Component
public class AnotacaoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Anotacao toDomainModel(AnotacaoInput anotacaoInput) {
		return modelMapper.map(anotacaoInput, Anotacao.class);
	}

	public void copyToDomainModel(AnotacaoInput anotacaoInput, Anotacao anotacao) {
		modelMapper.map(anotacaoInput, anotacao);
	}
}
