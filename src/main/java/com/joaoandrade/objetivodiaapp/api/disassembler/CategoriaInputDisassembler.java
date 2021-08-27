package com.joaoandrade.objetivodiaapp.api.disassembler;

import org.modelmapper.ModelMapper;

import com.joaoandrade.objetivodiaapp.api.input.CategoriaInput;
import com.joaoandrade.objetivodiaapp.domain.model.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Categoria toDomainModel(CategoriaInput categoriaInput) {
		return modelMapper.map(categoriaInput, Categoria.class);
	}

	public void copyToDomainModel(CategoriaInput categoriaInput, Categoria categoria) {
		modelMapper.map(categoriaInput, categoria);
	}
}
