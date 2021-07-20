package br.com.academy.crud.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.academy.crud.model.ProdutoVO;
import br.com.academy.crud.service.ProdutoService;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService service;
	
	@Autowired
	private PagedResourcesAssembler<ProdutoVO> assembler;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProdutoVO> create(@RequestBody @Valid ProdutoVO produto) {
		
		ProdutoVO produtoVO = service.create(produto);
		produtoVO.add(linkTo(methodOn(ProdutoController.class).findById(produtoVO.getId())).withSelfRel());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoVO);
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "12") Integer limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction,
			@RequestParam(value = "column", defaultValue = "nome") String column) {
		
		var sorDirection = Direction.DESC.name().equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorDirection, column));
		
		Page<ProdutoVO> produtos = service.findAll(pageable);
		
		produtos.stream().forEach(p -> p.add(linkTo(methodOn(ProdutoController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<ProdutoVO>> pagedModel = assembler.toModel(produtos);
		
		return ResponseEntity.ok(pagedModel);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoVO> findById(@PathVariable Long id) {
		
		ProdutoVO produtoVO = service.findById(id);
		produtoVO.add(linkTo(methodOn(ProdutoController.class).findById(id)).withSelfRel());
		
		return ResponseEntity.ok(produtoVO);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Long id) {
		
		ProdutoVO produtoVO = service.update(id);
		
		produtoVO.add(linkTo(methodOn(ProdutoController.class).findById(produtoVO.getId())).withSelfRel());
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
