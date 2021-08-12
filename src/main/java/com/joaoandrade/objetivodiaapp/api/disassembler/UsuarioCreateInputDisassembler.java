package com.joaoandrade.objetivodiaapp.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.api.input.UsuarioCreateInput;
import com.joaoandrade.objetivodiaapp.domain.model.Usuario;

@Component
public class UsuarioCreateInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Usuario toDomainModel(UsuarioCreateInput usuarioCreateInput) {
		return modelMapper.map(usuarioCreateInput, Usuario.class);
	}
}
