package com.joaoandrade.objetivodiaapp.domain.service.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.domain.exception.NegocioException;
import com.joaoandrade.objetivodiaapp.domain.model.Categoria;
import com.joaoandrade.objetivodiaapp.domain.repository.CategoriaRepository;

@Component
public class NomeCategoriaUnicoValidacao {

	@Autowired
	private CategoriaRepository repository;

	public void validarNomeCategoriaUnico(Categoria categoria) {
		Optional<Categoria> obj = repository.findByNome(categoria.getNome());

		if (obj.isPresent() && !obj.get().equals(categoria)) {
			throw new NegocioException(String.format("Já existe uma categoria com o nome'%s' cadastrado no sistema!",
					categoria.getNome()));
		}
	}
}
