package com.joaoandrade.objetivodiaapp.domain.service.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandrade.objetivodiaapp.domain.exception.AnotacaoNaoEncontradaException;
import com.joaoandrade.objetivodiaapp.domain.exception.NegocioException;
import com.joaoandrade.objetivodiaapp.domain.model.Anotacao;
import com.joaoandrade.objetivodiaapp.domain.repository.AnotacaoRepository;

@Service
public class CrudAnotacaoService {

	@Autowired
	private AnotacaoRepository repository;

	@Autowired
	private CrudUsuarioService crudUsuarioService;

	public Page<Anotacao> buscarTodasAnotacoesDoUsuario(Long usuarioId, Pageable pageable) {
		return repository.buscarAnotacoesDoUsuario(usuarioId, pageable);
	}

	public Page<Anotacao> buscarAnotacoesDoUsuarioPeloTitulo(Long usuarioId, String titulo, Pageable pageable) {
		return repository.buscarAnotacoesDoUsuarioPeloTitulo(usuarioId, titulo, pageable);
	}

	public Anotacao buscarAnotacaoDoUsuario(Long usuarioId, Long anotacaoId) {
		crudUsuarioService.buscarPorId(usuarioId);
		repository.findById(anotacaoId).orElseThrow(() -> new AnotacaoNaoEncontradaException(anotacaoId));

		return repository.buscarAnotacaoDoUsuario(usuarioId, anotacaoId).orElseThrow(() -> new NegocioException(String
				.format("A Anotação de id %d não estar associada com o Usuario de id %d", anotacaoId, usuarioId)));
	}

	@Transactional
	public Anotacao salvar(Anotacao anotacao) {
		return repository.save(anotacao);
	}

	@Transactional
	public void deletarAnotacaoDoUsuario(Long usuarioId, Long anotacaoId) {
		buscarAnotacaoDoUsuario(usuarioId, anotacaoId);

		repository.deleteById(anotacaoId);
	}

}
