package com.joaoandrade.objetivodiaapp.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joaoandrade.objetivodiaapp.api.assembler.ArtigoFullModelAssembler;
import com.joaoandrade.objetivodiaapp.api.assembler.ArtigoModelAssembler;
import com.joaoandrade.objetivodiaapp.api.disassembler.ArtigoInputDisassembler;
import com.joaoandrade.objetivodiaapp.api.input.ArtigoInput;
import com.joaoandrade.objetivodiaapp.api.model.ArtigoFullModel;
import com.joaoandrade.objetivodiaapp.api.model.ArtigoModel;
import com.joaoandrade.objetivodiaapp.domain.exception.CategoriaNaoEncontradaException;
import com.joaoandrade.objetivodiaapp.domain.exception.NegocioException;
import com.joaoandrade.objetivodiaapp.domain.model.Artigo;
import com.joaoandrade.objetivodiaapp.domain.service.crud.CrudArtigoService;

@RestController
@RequestMapping("/artigos")
public class ArtigoController {

	@Autowired
	private CrudArtigoService crudArtigoService;

	@Autowired
	private ArtigoModelAssembler artigoModelAssembler;

	@Autowired
	private ArtigoFullModelAssembler artigoFullModelAssembler;

	@Autowired
	private ArtigoInputDisassembler artigoInputDisassembler;

	@GetMapping
	public ResponseEntity<Page<ArtigoModel>> buscarTodos(Pageable pageable) {
		Page<Artigo> page = crudArtigoService.buscarTodos(pageable);

		return ResponseEntity.ok().cacheControl(CacheControl.noCache())
				.body(page.map(x -> artigoModelAssembler.toModel(x)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ArtigoFullModel> buscarPorId(@PathVariable Long id) {
		Artigo artigo = crudArtigoService.buscarPorId(id);

		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(artigoFullModelAssembler.toModel(artigo));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ArtigoFullModel salvar(@Valid @RequestBody ArtigoInput artigoInput) {
		try {
			Artigo artigo = crudArtigoService.salvar(artigoInputDisassembler.toDomainModel(artigoInput));

			return artigoFullModelAssembler.toModel(artigo);
		} catch (CategoriaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ArtigoFullModel atualizar(@Valid @RequestBody ArtigoInput artigoInput, @PathVariable Long id) {
		try {
			Artigo atual = crudArtigoService.buscarPorId(id);
			artigoInputDisassembler.copyToDomainModel(artigoInput, atual);
			atual = crudArtigoService.atualizar(atual);

			return artigoFullModelAssembler.toModel(atual);
		} catch (CategoriaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id) {
		crudArtigoService.deletarPorId(id);
	}
}
