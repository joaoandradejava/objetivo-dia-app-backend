package com.joaoandrade.objetivodiaapp.domain.service.crud;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoandrade.objetivodiaapp.domain.exception.CategoriaNaoEncontradaException;
import com.joaoandrade.objetivodiaapp.domain.exception.EntidadeEmUsoException;
import com.joaoandrade.objetivodiaapp.domain.model.Categoria;
import com.joaoandrade.objetivodiaapp.domain.repository.CategoriaRepository;
import com.joaoandrade.objetivodiaapp.domain.service.validation.NomeCategoriaUnicoValidacao;

@Service
public class CrudCategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Autowired
	private NomeCategoriaUnicoValidacao nomeCategoriaUnicoValidacao;

	@Autowired
	private EntityManager entityManager;

	public List<Categoria> buscarTodos() {
		return repository.findAll();
	}

	public Categoria buscarPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new CategoriaNaoEncontradaException(id));
	}

	@Transactional
	public Categoria salvar(Categoria categoria) {
		nomeCategoriaUnicoValidacao.validarNomeCategoriaUnico(categoria);

		return repository.save(categoria);
	}

	@Transactional
	public Categoria atualizar(Categoria categoria) {
		entityManager.detach(categoria);
		nomeCategoriaUnicoValidacao.validarNomeCategoriaUnico(categoria);

		return repository.save(categoria);
	}

	@Transactional
	public void deletarPorId(Long id) {
		Categoria categoria = buscarPorId(id);

		try {
			repository.deleteById(id);
			repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(
					"Não foi possivel deletar a categoria '%s' pois ela tem os Objetivos dos usuarios que estão associados a ela!",
					categoria.getNome()));
		}
	}

}
