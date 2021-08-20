package com.joaoandrade.objetivodiaapp.domain.service.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandrade.objetivodiaapp.domain.exception.NegocioException;
import com.joaoandrade.objetivodiaapp.domain.exception.ObjetivoNaoEncontradoException;
import com.joaoandrade.objetivodiaapp.domain.model.Objetivo;
import com.joaoandrade.objetivodiaapp.domain.repository.ObjetivoRepository;

@Service
public class CrudObjetivoService {

	@Autowired
	private ObjetivoRepository repository;

	@Autowired
	private CrudUsuarioService crudUsuarioService;

	public Page<Objetivo> buscarObjetivos(Long usuarioId, Pageable pageable) {
		crudUsuarioService.buscarPorId(usuarioId);

		return repository.buscarObjetivos(usuarioId, pageable);
	}

	public List<Objetivo> buscarObjetivos(Long usuarioId) {
		crudUsuarioService.buscarPorId(usuarioId);

		return repository.buscarObjetivosDoUsuario(usuarioId);
	}

	public Objetivo buscarObjetivoPorId(Long usuarioId, Long objetivoId) {
		crudUsuarioService.buscarPorId(usuarioId);
		repository.findById(objetivoId).orElseThrow(() -> new ObjetivoNaoEncontradoException(objetivoId));

		return repository.buscarObjetivoDoUsuario(usuarioId, objetivoId).orElseThrow(() -> new NegocioException(String
				.format("O Objetivo de id %d não estar associado com o Usuario de id %d", objetivoId, usuarioId)));
	}

	@Transactional
	public Objetivo salvar(Objetivo objetivo, Long usuarioId) {
		objetivo.setUsuario(crudUsuarioService.buscarPorId(usuarioId));

		return repository.save(objetivo);
	}

	@Transactional
	public Objetivo atualizar(Objetivo objetivo) {
		return repository.save(objetivo);
	}

	@Transactional
	public void deletarObjetivoPorId(Long usuarioId, Long objetivoId) {
		buscarObjetivoPorId(usuarioId, objetivoId);

		repository.deleteById(objetivoId);
	}

}
