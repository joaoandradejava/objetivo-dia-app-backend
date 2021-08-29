package com.joaoandrade.objetivodiaapp.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.api.model.ArtigoFullModel;
import com.joaoandrade.objetivodiaapp.domain.model.Artigo;

@Component
public class ArtigoFullModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public ArtigoFullModel toModel(Artigo artigo) {
		return modelMapper.map(artigo, ArtigoFullModel.class);
	}

	public List<ArtigoFullModel> toCollectionModel(List<Artigo> lista) {
		return lista.stream().map(x -> toModel(x)).collect(Collectors.toList());
	}
}
