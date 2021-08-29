package com.joaoandrade.objetivodiaapp.domain.service.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.joaoandrade.objetivodiaapp.domain.exception.NegocioException;
import com.joaoandrade.objetivodiaapp.domain.model.Artigo;
import com.joaoandrade.objetivodiaapp.domain.repository.ArtigoRepository;

@Component
public class TituloArtigoUnicoValidacao {

	@Autowired
	private ArtigoRepository repository;

	public void validarTituloArtigoUnico(Artigo artigo) {
		Optional<Artigo> obj = repository.buscarPorTitulo(artigo.getTitulo());

		if (obj.isPresent() && !obj.get().equals(artigo)) {
			throw new NegocioException(String.format("Já existe um artigo com o titulo '%s'", artigo.getTitulo()));
		}
	}
}
