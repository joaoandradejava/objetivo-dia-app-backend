package com.joaoandrade.objetivodiaapp.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joaoandrade.objetivodiaapp.api.assembler.UsuarioModelAssembler;
import com.joaoandrade.objetivodiaapp.api.disassembler.UsuarioCreateInputDisassembler;
import com.joaoandrade.objetivodiaapp.api.disassembler.UsuarioUpdateInputDisassembler;
import com.joaoandrade.objetivodiaapp.api.input.UsuarioCreateInput;
import com.joaoandrade.objetivodiaapp.api.input.UsuarioUpdateInput;
import com.joaoandrade.objetivodiaapp.api.model.UsuarioModel;
import com.joaoandrade.objetivodiaapp.domain.exception.NegocioException;
import com.joaoandrade.objetivodiaapp.domain.model.Usuario;
import com.joaoandrade.objetivodiaapp.domain.service.CrudUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private CrudUsuarioService crudUsuarioService;

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@Autowired
	private UsuarioCreateInputDisassembler usuarioCreateInputDisassembler;

	@Autowired
	private UsuarioUpdateInputDisassembler usuarioUpdateInputDisassembler;

	@GetMapping("/{id}/resumo")
	public UsuarioModel buscarPorIdResumido(@PathVariable Long id) {
		Usuario usuario = crudUsuarioService.buscarPorId(id);

		return usuarioModelAssembler.toModel(usuario);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public UsuarioModel salvar(@Valid @RequestBody UsuarioCreateInput usuarioCreateInput) {
		boolean senhaValida = usuarioCreateInput.getSenha().equals(usuarioCreateInput.getConfirmacaoSenha());
		if (!senhaValida) {
			throw new NegocioException("As senhas não correspondem");
		}
		Usuario usuario = crudUsuarioService.salvar(usuarioCreateInputDisassembler.toDomainModel(usuarioCreateInput));

		return usuarioModelAssembler.toModel(usuario);
	}

	@PutMapping("/{id}")
	public UsuarioModel atualizar(@Valid @RequestBody UsuarioUpdateInput usuarioUpdateInput, @PathVariable Long id) {
		Usuario atual = crudUsuarioService.buscarPorId(id);
		usuarioUpdateInputDisassembler.copyToDomainModel(usuarioUpdateInput, atual);
		atual = crudUsuarioService.atualizar(atual);

		return usuarioModelAssembler.toModel(atual);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id) {
		crudUsuarioService.deletarPorId(id);
	}

}
