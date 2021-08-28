package com.joaoandrade.objetivodiaapp.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.api.model.UsuarioPerfilModel;
import com.joaoandrade.objetivodiaapp.domain.model.Usuario;

@Component
public class UsuarioPerfilModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public UsuarioPerfilModel toModel(Usuario usuario) {
		UsuarioPerfilModel usuarioPerfilModel = modelMapper.map(usuario, UsuarioPerfilModel.class);
		usuarioPerfilModel.setPerfil(usuario.pegarOTipoDePerfil());

		return usuarioPerfilModel;
	}

	public List<UsuarioPerfilModel> toCollectionModel(List<Usuario> lista) {
		return lista.stream().map(x -> toModel(x)).collect(Collectors.toList());
	}
}
