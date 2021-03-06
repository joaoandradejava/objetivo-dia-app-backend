package com.joaoandrade.objetivodiaapp.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.joaoandrade.objetivodiaapp.api.assembler.TarefaModelAssembler;
import com.joaoandrade.objetivodiaapp.api.disassembler.TarefaInputDisassembler;
import com.joaoandrade.objetivodiaapp.api.input.TarefaInput;
import com.joaoandrade.objetivodiaapp.api.model.TarefaModel;
import com.joaoandrade.objetivodiaapp.core.security.UsuarioLogado;
import com.joaoandrade.objetivodiaapp.domain.model.Tarefa;
import com.joaoandrade.objetivodiaapp.domain.service.PermissaoAcessoService;
import com.joaoandrade.objetivodiaapp.domain.service.TarefaService;
import com.joaoandrade.objetivodiaapp.domain.service.crud.CrudTarefaService;

@RestController
@RequestMapping("/objetivos/{objetivoId}/tarefas")
public class ObjetivoTarefaController {

	@Autowired
	private CrudTarefaService crudTarefaService;

	@Autowired
	private TarefaModelAssembler tarefaModelAssembler;

	@Autowired
	private TarefaInputDisassembler tarefaInputDisassembler;

	@Autowired
	private TarefaService tarefaService;

	@Autowired
	private PermissaoAcessoService permissaoAcessoService;

	private void verificarSeTemPermissao(Long objetivoId, UsuarioLogado usuarioLogado) {
		Long usuarioId = crudTarefaService.buscarObjetivoPorId(objetivoId).getUsuario().getId();
		permissaoAcessoService.verificarSeTemPermissao(usuarioId, usuarioLogado,
				"Voc?? n??o tem permiss??o para acessar as tarefas de outro usuario");
	}

	@GetMapping
	public ResponseEntity<List<TarefaModel>> buscarTodasTarefasDoObjetivo(@PathVariable Long objetivoId,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		verificarSeTemPermissao(objetivoId, usuarioLogado);

		List<Tarefa> lista = crudTarefaService.buscarTodasTarefasDoObjetivo(objetivoId);

		return ResponseEntity.ok().cacheControl(CacheControl.noCache())
				.body(lista.stream().map(x -> tarefaModelAssembler.toModel(x)).collect(Collectors.toList()));
	}

	@GetMapping("/{tarefaId}")
	public TarefaModel buscarTarefaPorId(@PathVariable Long objetivoId, @PathVariable Long tarefaId,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		verificarSeTemPermissao(objetivoId, usuarioLogado);

		Tarefa tarefa = crudTarefaService.buscarTarefaPorId(objetivoId, tarefaId);

		return tarefaModelAssembler.toModel(tarefa);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public TarefaModel salvar(@Valid @RequestBody TarefaInput tarefaInput, @PathVariable Long objetivoId,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		verificarSeTemPermissao(objetivoId, usuarioLogado);

		Tarefa tarefa = crudTarefaService.salvar(tarefaInputDisassembler.toDomainModel(tarefaInput), objetivoId);

		return tarefaModelAssembler.toModel(tarefa);
	}

	@DeleteMapping("/{tarefaId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarTarefaPorId(@PathVariable Long objetivoId, @PathVariable Long tarefaId,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		verificarSeTemPermissao(objetivoId, usuarioLogado);

		crudTarefaService.deletarTarefaPorId(objetivoId, tarefaId);
	}

	@PutMapping("/{tarefaId}/feita")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void fazerTarefa(@PathVariable Long objetivoId, @PathVariable Long tarefaId,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		verificarSeTemPermissao(objetivoId, usuarioLogado);

		tarefaService.fazerTarefa(objetivoId, tarefaId);
	}

	@DeleteMapping("/{tarefaId}/feita")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void desfazerTarefa(@PathVariable Long objetivoId, @PathVariable Long tarefaId,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		verificarSeTemPermissao(objetivoId, usuarioLogado);

		tarefaService.desfazerTarefa(objetivoId, tarefaId);
	}
}
