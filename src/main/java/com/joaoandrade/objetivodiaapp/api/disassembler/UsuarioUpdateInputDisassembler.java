package com.joaoandrade.objetivodiaapp.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.api.input.UsuarioUpdateInput;
import com.joaoandrade.objetivodiaapp.domain.model.Usuario;

@Component
public class UsuarioUpdateInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public void copyToDomainModel(UsuarioUpdateInput usuarioUpdateInput, Usuario usuario) {
		modelMapper.map(usuarioUpdateInput, usuario);
	}
}
