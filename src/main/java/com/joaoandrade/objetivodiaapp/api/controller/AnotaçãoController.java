package com.joaoandrade.objetivodiaapp.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joaoandrade.objetivodiaapp.api.assembler.AnotacaoFullModelAssembler;
import com.joaoandrade.objetivodiaapp.api.assembler.AnotacaoModelAssembler;
import com.joaoandrade.objetivodiaapp.api.disassembler.AnotacaoInputDisassembler;
import com.joaoandrade.objetivodiaapp.api.input.AnotacaoInput;
import com.joaoandrade.objetivodiaapp.api.model.AnotacaoFullModel;
import com.joaoandrade.objetivodiaapp.api.model.AnotacaoModel;
import com.joaoandrade.objetivodiaapp.core.security.UsuarioLogado;
import com.joaoandrade.objetivodiaapp.domain.model.Anotacao;
import com.joaoandrade.objetivodiaapp.domain.service.PermissaoAcessoService;
import com.joaoandrade.objetivodiaapp.domain.service.crud.CrudAnotacaoService;
import com.joaoandrade.objetivodiaapp.domain.service.crud.CrudUsuarioService;

@RestController
@RequestMapping("/usuarios/{usuarioId}/anotacoes")
public class AnotaçãoController {

	@Autowired
	private CrudAnotacaoService crudAnotacaoService;

	@Autowired
	private AnotacaoModelAssembler anotacaoModelAssembler;

	@Autowired
	private AnotacaoFullModelAssembler anotacaoFullModelAssembler;

	@Autowired
	private AnotacaoInputDisassembler anotacaoInputDisassembler;

	@Autowired
	private CrudUsuarioService crudUsuarioService;

	@Autowired
	private PermissaoAcessoService permissaoAcessoService;

	@GetMapping
	public Page<AnotacaoModel> buscarTodasAnotacoesDoUsuario(@PathVariable Long usuarioId,
			@PageableDefault(size = 6) Pageable pageable, String titulo,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		permissaoAcessoService.verificarSeTemPermissao(usuarioId, usuarioLogado,
				"Você não tem permissão para mecher nas anotaçoes de outro usuario no sistema!");

		Page<Anotacao> page = null;

		if (StringUtils.hasLength(titulo)) {
			page = crudAnotacaoService.buscarAnotacoesDoUsuarioPeloTitulo(usuarioId, titulo, pageable);

			return page.map(x -> anotacaoModelAssembler.toModel(x));
		}

		page = crudAnotacaoService.buscarTodasAnotacoesDoUsuario(usuarioId, pageable);

		return page.map(x -> anotacaoModelAssembler.toModel(x));
	}

	@GetMapping("/{anotacaoId}")
	public AnotacaoFullModel buscarAnotacaoDoUsuario(@PathVariable Long usuarioId, @PathVariable Long anotacaoId,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		permissaoAcessoService.verificarSeTemPermissao(usuarioId, usuarioLogado,
				"Você não tem permissão para mecher nas anotaçoes de outro usuario no sistema!");

		Anotacao anotacao = crudAnotacaoService.buscarAnotacaoDoUsuario(usuarioId, anotacaoId);

		return anotacaoFullModelAssembler.toModel(anotacao);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public AnotacaoFullModel salvar(@Valid @RequestBody AnotacaoInput anotacaoInput, @PathVariable Long usuarioId,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		permissaoAcessoService.verificarSeTemPermissao(usuarioId, usuarioLogado,
				"Você não tem permissão para mecher nas anotaçoes de outro usuario no sistema!");

		Anotacao anotacao = anotacaoInputDisassembler.toDomainModel(anotacaoInput);
		anotacao.setUsuario(crudUsuarioService.buscarPorId(usuarioId));
		anotacao = crudAnotacaoService.salvar(anotacao);

		return anotacaoFullModelAssembler.toModel(anotacao);
	}

	@PutMapping("/{anotacaoId}")
	public AnotacaoFullModel atualizar(@Valid @RequestBody AnotacaoInput anotacaoInput, @PathVariable Long usuarioId,
			@PathVariable Long anotacaoId, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		permissaoAcessoService.verificarSeTemPermissao(usuarioId, usuarioLogado,
				"Você não tem permissão para mecher nas anotaçoes de outro usuario no sistema!");

		Anotacao anotacao = crudAnotacaoService.buscarAnotacaoDoUsuario(usuarioId, anotacaoId);
		anotacaoInputDisassembler.copyToDomainModel(anotacaoInput, anotacao);
		anotacao = crudAnotacaoService.salvar(anotacao);

		return anotacaoFullModelAssembler.toModel(anotacao);
	}

	@DeleteMapping("/{anotacaoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarAnotacaoDoUsuario(@PathVariable Long usuarioId, @PathVariable Long anotacaoId,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		permissaoAcessoService.verificarSeTemPermissao(usuarioId, usuarioLogado,
				"Você não tem permissão para mecher nas anotaçoes de outro usuario no sistema!");

		crudAnotacaoService.deletarAnotacaoDoUsuario(usuarioId, anotacaoId);
	}

}
