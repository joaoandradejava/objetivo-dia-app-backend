package com.joaoandrade.objetivodiaapp.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joaoandrade.objetivodiaapp.api.assembler.FeedbackFullModelAssembler;
import com.joaoandrade.objetivodiaapp.api.disassembler.FeedbackInputDisassembler;
import com.joaoandrade.objetivodiaapp.api.input.FeedbackInput;
import com.joaoandrade.objetivodiaapp.api.model.FeedbackFullModel;
import com.joaoandrade.objetivodiaapp.core.security.UsuarioLogado;
import com.joaoandrade.objetivodiaapp.domain.model.Feedback;
import com.joaoandrade.objetivodiaapp.domain.service.PermissaoAcessoService;
import com.joaoandrade.objetivodiaapp.domain.service.crud.CrudUsuarioFeedbackService;
import com.joaoandrade.objetivodiaapp.domain.service.crud.CrudUsuarioService;

@RestController
@RequestMapping("/usuarios/{usuarioId}/feedbacks")
public class UsuarioFeedbackController {

	@Autowired
	private CrudUsuarioFeedbackService crudUsuarioFeedbackService;

	@Autowired
	private CrudUsuarioService crudUsuarioService;

	@Autowired
	private FeedbackInputDisassembler feedbackInputDisassembler;

	@Autowired
	private FeedbackFullModelAssembler feedbackFullModelAssembler;

	@Autowired
	private PermissaoAcessoService permissaoAcessoService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public FeedbackFullModel salvar(@Valid @RequestBody FeedbackInput feedbackInput, @PathVariable Long usuarioId,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado) {
		permissaoAcessoService.verificarSeTemPermissao(usuarioId, usuarioLogado,
				"Você não tem permissão para adicionar o feedback em nome de outro usuario");

		Feedback feedback = feedbackInputDisassembler.toDomainModel(feedbackInput);
		feedback.setUsuario(crudUsuarioService.buscarPorId(usuarioId));

		feedback = crudUsuarioFeedbackService.salvar(feedback);

		return feedbackFullModelAssembler.toModel(feedback);
	}

}
