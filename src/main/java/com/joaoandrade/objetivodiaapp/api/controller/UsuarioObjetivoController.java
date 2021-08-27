package com.joaoandrade.objetivodiaapp.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joaoandrade.objetivodiaapp.api.assembler.ObjetivoFullModelAssembler;
import com.joaoandrade.objetivodiaapp.api.assembler.ObjetivoModelAssembler;
import com.joaoandrade.objetivodiaapp.api.disassembler.ObjetivoInputDisassembler;
import com.joaoandrade.objetivodiaapp.api.input.ObjetivoInput;
import com.joaoandrade.objetivodiaapp.api.model.ObjetivoFullModel;
import com.joaoandrade.objetivodiaapp.api.model.ObjetivoModel;
import com.joaoandrade.objetivodiaapp.core.security.UsuarioLogado;
import com.joaoandrade.objetivodiaapp.domain.exception.CategoriaNaoEncontradaException;
import com.joaoandrade.objetivodiaapp.domain.exception.NegocioException;
import com.joaoandrade.objetivodiaapp.domain.model.Objetivo;
import com.joaoandrade.objetivodiaapp.domain.service.PermissaoAcessoService;
import com.joaoandrade.objetivodiaapp.domain.service.crud.CrudObjetivoService;

@RestController
@RequestMapping("/usuarios/{usuarioId}/objetivos")
public class UsuarioObjetivoController {

	@Autowired
	private CrudObjetivoService crudObjetivoService;

	@Autowired
	private ObjetivoModelAssembler objetivoModelAssembler;

	@Autowired
	private ObjetivoFullModelAssembler objetivoFullModelAssembler;

	@Autowired
	private ObjetivoInputDisassembler objetivoInputDisassembler;

	@Autowired
	private PermissaoAcessoService permissaoAcessoService;

	@GetMapping
	public ResponseEntity<Page<ObjetivoModel>> buscarObjetivos(@PathVariable Long usuarioId,
			@PageableDefault(size = 10, sort = "data", direction = Direction.DESC) Pageable pageable,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		permissaoAcessoService.verificarSeTemPermissao(usuarioId, usuarioLogado,
				"Você não tem permissão para acessar os objetivos de outro usuario");

		Page<Objetivo> page = crudObjetivoService.buscarObjetivos(usuarioId, pageable);

		return ResponseEntity.ok().cacheControl(CacheControl.noCache())
				.body(page.map(objetivo -> objetivoModelAssembler.toModel(objetivo)));
	}

	@GetMapping("/{objetivoId}")
	public ResponseEntity<ObjetivoFullModel> buscarObjetivoPorId(@PathVariable Long usuarioId,
			@PathVariable Long objetivoId, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		permissaoAcessoService.verificarSeTemPermissao(usuarioId, usuarioLogado,
				"Você não tem permissão para acessar os objetivos de outro usuario");

		Objetivo objetivo = crudObjetivoService.buscarObjetivoPorId(usuarioId, objetivoId);

		return ResponseEntity.ok().cacheControl(CacheControl.noCache())
				.body(objetivoFullModelAssembler.toModel(objetivo));
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ObjetivoFullModel salvar(@Valid @RequestBody ObjetivoInput objetivoInput, @PathVariable Long usuarioId,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {

		permissaoAcessoService.verificarSeTemPermissao(usuarioId, usuarioLogado,
				"Você não tem permissão para acessar os objetivos de outro usuario");

		try {

			Objetivo objetivo = crudObjetivoService.salvar(objetivoInputDisassembler.toDomainModel(objetivoInput),
					usuarioId);

			return objetivoFullModelAssembler.toModel(objetivo);
		} catch (CategoriaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{objetivoId}")
	public ObjetivoFullModel atualizar(@Valid @RequestBody ObjetivoInput objetivoInput, @PathVariable Long usuarioId,
			@PathVariable Long objetivoId, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		permissaoAcessoService.verificarSeTemPermissao(usuarioId, usuarioLogado,
				"Você não tem permissão para acessar os objetivos de outro usuario");

		try {
			Objetivo objetivo = crudObjetivoService.buscarObjetivoPorId(usuarioId, objetivoId);
			objetivoInputDisassembler.copyToDomainModel(objetivoInput, objetivo);
			objetivo = crudObjetivoService.atualizar(objetivo);

			return objetivoFullModelAssembler.toModel(objetivo);
		} catch (CategoriaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@DeleteMapping("{objetivoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarObjetivoPorId(@PathVariable Long usuarioId, @PathVariable Long objetivoId,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		permissaoAcessoService.verificarSeTemPermissao(usuarioId, usuarioLogado,
				"Você não tem permissão para acessar os objetivos de outro usuario");

		crudObjetivoService.deletarObjetivoPorId(usuarioId, objetivoId);
	}

}
