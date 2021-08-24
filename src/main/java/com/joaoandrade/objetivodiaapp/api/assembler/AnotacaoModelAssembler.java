package com.joaoandrade.objetivodiaapp.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.api.model.AnotacaoModel;
import com.joaoandrade.objetivodiaapp.domain.model.Anotacao;

@Component
public class AnotacaoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public AnotacaoModel toModel(Anotacao anotacao) {
		AnotacaoModel anotacaoModel = modelMapper.map(anotacao, AnotacaoModel.class);
		anotacaoModel.setConteudoResumo(anotacao.pegarConteudoResumido());

		return anotacaoModel;
	}

}
