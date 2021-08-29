package com.joaoandrade.objetivodiaapp.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.api.input.ArtigoInput;
import com.joaoandrade.objetivodiaapp.domain.model.Artigo;
import com.joaoandrade.objetivodiaapp.domain.model.Categoria;

@Component
public class ArtigoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Artigo toDomainModel(ArtigoInput artigoInput) {
		return modelMapper.map(artigoInput, Artigo.class);
	}

	public void copyToDomainModel(ArtigoInput artigoInput, Artigo artigo) {
		artigo.setCategoria(new Categoria());

		modelMapper.map(artigoInput, artigo);
	}
}
