package com.joaoandrade.objetivodiaapp.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.joaoandrade.objetivodiaapp.api.assembler.UsuarioModelAssembler;
import com.joaoandrade.objetivodiaapp.api.assembler.UsuarioPerfilModelAssembler;
import com.joaoandrade.objetivodiaapp.api.disassembler.UsuarioCreateInputDisassembler;
import com.joaoandrade.objetivodiaapp.api.disassembler.UsuarioUpdateInputDisassembler;
import com.joaoandrade.objetivodiaapp.api.input.EsqueciSenhaInput;
import com.joaoandrade.objetivodiaapp.api.input.MudancaSenhaInput;
import com.joaoandrade.objetivodiaapp.api.input.UsuarioCreateInput;
import com.joaoandrade.objetivodiaapp.api.input.UsuarioUpdateInput;
import com.joaoandrade.objetivodiaapp.api.model.RelatorioModel;
import com.joaoandrade.objetivodiaapp.api.model.UsuarioModel;
import com.joaoandrade.objetivodiaapp.api.model.UsuarioPerfilModel;
import com.joaoandrade.objetivodiaapp.core.security.UsuarioLogado;
import com.joaoandrade.objetivodiaapp.domain.dto.GraficoObjetivoConcluidoDTO;
import com.joaoandrade.objetivodiaapp.domain.exception.ErroNoServidorException;
import com.joaoandrade.objetivodiaapp.domain.exception.NegocioException;
import com.joaoandrade.objetivodiaapp.domain.model.Usuario;
import com.joaoandrade.objetivodiaapp.domain.observer.EsqueciSenhaObserver;
import com.joaoandrade.objetivodiaapp.domain.repository.UsuarioRepository;
import com.joaoandrade.objetivodiaapp.domain.service.PermissaoAcessoService;
import com.joaoandrade.objetivodiaapp.domain.service.UsuarioService;
import com.joaoandrade.objetivodiaapp.domain.service.crud.CrudUsuarioService;
import com.joaoandrade.objetivodiaapp.domain.service.relatorio.RelatorioService;

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

	@Autowired
	private PermissaoAcessoService permissaoAcessoService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	private UsuarioPerfilModelAssembler usuarioPerfilModelAssembler;

	@Autowired
	private RelatorioService relatorioService;

	@Autowired
	private UsuarioRepository repository;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<Page<UsuarioPerfilModel>> buscarTodos(Pageable pageable, String nome, String email) {
		Page<Usuario> page = null;
		if (StringUtils.hasLength(nome)) {
			page = crudUsuarioService.buscarPorNome(nome, pageable);

			return ResponseEntity.ok().cacheControl(CacheControl.noCache())
					.body(page.map(x -> usuarioPerfilModelAssembler.toModel(x)));

		}

		if (StringUtils.hasLength(email)) {
			page = crudUsuarioService.buscarPorEmail(email, pageable);

			return ResponseEntity.ok().cacheControl(CacheControl.noCache())
					.body(page.map(x -> usuarioPerfilModelAssembler.toModel(x)));

		}

		page = crudUsuarioService.buscarTodos(pageable);
		return ResponseEntity.ok().cacheControl(CacheControl.noCache())
				.body(page.map(x -> usuarioPerfilModelAssembler.toModel(x)));
	}

	@GetMapping("/{id}/resumo")
	public ResponseEntity<UsuarioModel> buscarPorIdResumido(@PathVariable Long id,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		permissaoAcessoService.verificarSeTemPermissao(id, usuarioLogado,
				"Você não tem permissão para acessar os dados de outros usuario");

		Usuario usuario = crudUsuarioService.buscarPorId(id);

		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(usuarioModelAssembler.toModel(usuario));
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
	public UsuarioModel atualizar(@Valid @RequestBody UsuarioUpdateInput usuarioUpdateInput, @PathVariable Long id,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		permissaoAcessoService.verificarSeTemPermissao(id, usuarioLogado,
				"Você não tem permissão para atualizar os dados de outros usuario!");

		Usuario atual = crudUsuarioService.buscarPorId(id);
		usuarioUpdateInputDisassembler.copyToDomainModel(usuarioUpdateInput, atual);
		atual = crudUsuarioService.atualizar(atual);

		return usuarioModelAssembler.toModel(atual);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		permissaoAcessoService.verificarSeTemPermissao(id, usuarioLogado,
				"Você não tem permissão para deletar a conta de outro usuario");

		crudUsuarioService.deletarPorId(id);
	}

	@PutMapping("/{id}/senha")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void mudarSenha(@PathVariable Long id, @Valid @RequestBody MudancaSenhaInput mudancaSenhaInput,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		permissaoAcessoService.verificarSeTemPermissao(id, usuarioLogado,
				"Você não tem permissão para alterar a senha de outro usuario!");

		usuarioService.mudarSenha(id, mudancaSenhaInput.getSenhaAtual(), mudancaSenhaInput.getNovaSenha(),
				mudancaSenhaInput.getConfirmacaoSenha());
	}

	@PutMapping("/esqueci-senha")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void esqueciASenha(@Valid @RequestBody EsqueciSenhaInput esqueciSenhaInput) {
		String novaSenha = usuarioService.esqueciASenha(esqueciSenhaInput.getEmail());
		Usuario usuario = crudUsuarioService.buscarPorEmail(esqueciSenhaInput.getEmail());

		applicationEventPublisher.publishEvent(new EsqueciSenhaObserver(usuario, novaSenha));
	}

	@GetMapping("/{id}/objetivos/grafico-conclusao")
	public ResponseEntity<GraficoObjetivoConcluidoDTO> obterGraficoDosObjetivosConcluidos(@PathVariable Long id) {
		GraficoObjetivoConcluidoDTO graficoObjetivoConcluidoDTO = usuarioService.obterGraficoDosObjetivosConcluidos(id);

		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.body(graficoObjetivoConcluidoDTO);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}/admin")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void darAcessoDeAdministrador(@PathVariable Long id) {
		usuarioService.darAcessoDeAdministrador(id);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}/admin")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removerAcessoDeAdministrador(@PathVariable Long id) {
		usuarioService.removerAcessoDeAdministrador(id);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/usuario-perfil/relatorio")
	public RelatorioModel gerarRelatorioPerfil() {
		try {
			Map<String, Object> parametros = new HashMap<>();

			List<UsuarioPerfilModel> lista = usuarioPerfilModelAssembler.toCollectionModel(repository.findAll());
			String relatorioPdf = relatorioService.gerarRelatorio("perfil-usuario", parametros, lista);

			return new RelatorioModel(relatorioPdf);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErroNoServidorException(e.getMessage());
		}
	}
}
