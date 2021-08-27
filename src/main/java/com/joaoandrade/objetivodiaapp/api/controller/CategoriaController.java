package com.joaoandrade.objetivodiaapp.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.joaoandrade.objetivodiaapp.api.assembler.CategoriaModelAssembler;
import com.joaoandrade.objetivodiaapp.api.disassembler.CategoriaInputDisassembler;
import com.joaoandrade.objetivodiaapp.api.input.CategoriaInput;
import com.joaoandrade.objetivodiaapp.api.model.CategoriaModel;
import com.joaoandrade.objetivodiaapp.domain.model.Categoria;
import com.joaoandrade.objetivodiaapp.domain.service.crud.CrudCategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CrudCategoriaService crudCategoriaService;

	@Autowired
	private CategoriaModelAssembler categoriaModelAssembler;

	@Autowired
	private CategoriaInputDisassembler categoriaInputDisassembler;

	@GetMapping
	public ResponseEntity<List<CategoriaModel>> buscarTodos() {
		List<Categoria> lista = crudCategoriaService.buscarTodos();

		return ResponseEntity.ok().cacheControl(CacheControl.noCache())
				.body(categoriaModelAssembler.toCollection(lista));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaModel> buscarPorId(@PathVariable Long id) {
		Categoria categoria = crudCategoriaService.buscarPorId(id);

		return ResponseEntity.ok().cacheControl(CacheControl.noCache())
				.body(categoriaModelAssembler.toModel(categoria));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public CategoriaModel salvar(@Valid @RequestBody CategoriaInput categoriaInput) {
		Categoria categoria = crudCategoriaService.salvar(categoriaInputDisassembler.toDomainModel(categoriaInput));

		return categoriaModelAssembler.toModel(categoria);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public CategoriaModel atualizar(@Valid @RequestBody CategoriaInput categoriaInput, @PathVariable Long id) {
		Categoria atual = crudCategoriaService.buscarPorId(id);
		categoriaInputDisassembler.copyToDomainModel(categoriaInput, atual);
		atual = crudCategoriaService.atualizar(atual);

		return categoriaModelAssembler.toModel(atual);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id) {
		crudCategoriaService.deletarPorId(id);
	}
}
