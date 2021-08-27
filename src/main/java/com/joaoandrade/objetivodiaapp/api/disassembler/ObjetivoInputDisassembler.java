package com.joaoandrade.objetivodiaapp.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.api.input.ObjetivoInput;
import com.joaoandrade.objetivodiaapp.domain.model.Categoria;
import com.joaoandrade.objetivodiaapp.domain.model.Objetivo;

@Component
public class ObjetivoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Objetivo toDomainModel(ObjetivoInput objetivoInput) {
		return modelMapper.map(objetivoInput, Objetivo.class);
	}

	public void copyToDomainModel(ObjetivoInput objetivoInput, Objetivo objetivo) {
		objetivo.setCategoria(new Categoria());

		modelMapper.map(objetivoInput, objetivo);
	}
}
