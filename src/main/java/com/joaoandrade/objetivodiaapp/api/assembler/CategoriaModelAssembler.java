package com.joaoandrade.objetivodiaapp.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.api.model.CategoriaModel;
import com.joaoandrade.objetivodiaapp.domain.model.Categoria;

@Component
public class CategoriaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public CategoriaModel toModel(Categoria categoria) {
		return modelMapper.map(categoria, CategoriaModel.class);
	}

	public List<CategoriaModel> toCollection(List<Categoria> lista) {
		return lista.stream().map(x -> toModel(x)).collect(Collectors.toList());
	}
}
