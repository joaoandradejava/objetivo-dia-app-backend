package com.joaoandrade.objetivodiaapp.domain.service.crud;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandrade.objetivodiaapp.domain.exception.ArtigoNaoEncontradoException;
import com.joaoandrade.objetivodiaapp.domain.model.Artigo;
import com.joaoandrade.objetivodiaapp.domain.repository.ArtigoRepository;
import com.joaoandrade.objetivodiaapp.domain.service.validation.TituloArtigoUnicoValidacao;

@Service
public class CrudArtigoService {

	@Autowired
	private ArtigoRepository repository;

	@Autowired
	private CrudCategoriaService crudCategoriaService;

	@Autowired
	private TituloArtigoUnicoValidacao tituloArtigoUnicoValidacao;

	@Autowired
	private EntityManager entityManager;

	public Page<Artigo> buscarTodos(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Artigo buscarPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new ArtigoNaoEncontradoException(id));
	}

	@Transactional
	public Artigo salvar(Artigo artigo) {
		tituloArtigoUnicoValidacao.validarTituloArtigoUnico(artigo);
		artigo.setCategoria(crudCategoriaService.buscarPorId(artigo.getCategoria().getId()));

		return repository.save(artigo);
	}

	@Transactional
	public Artigo atualizar(Artigo artigo) {
		entityManager.detach(artigo);
		tituloArtigoUnicoValidacao.validarTituloArtigoUnico(artigo);
		artigo.setCategoria(crudCategoriaService.buscarPorId(artigo.getCategoria().getId()));

		return repository.save(artigo);
	}

	@Transactional
	public void deletarPorId(Long id) {
		buscarPorId(id);

		repository.deleteById(id);
	}
}
