package br.com.academy.pagemento.controller;

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

import br.com.academy.pagemento.model.VendaVO;
import br.com.academy.pagemento.service.VendaService;

@RestController
@RequestMapping(path = "/vendas")
public class VendaController {
	
	@Autowired
	private VendaService service;
	
	@Autowired
	private PagedResourcesAssembler<VendaVO> assembler;

	@PostMapping
	public ResponseEntity<VendaVO> create(@RequestBody @Valid VendaVO vendaVO) {
		
		VendaVO produtoVO = service.create(vendaVO);
		produtoVO.add(linkTo(methodOn(VendaController.class).findById(produtoVO.getId())).withSelfRel());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoVO);
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "12") Integer limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction,
			@RequestParam(value = "column", defaultValue = "dataVenda") String column) {
		
		var sorDirection = Direction.DESC.name().equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sorDirection, column));
		
		Page<VendaVO> produtos = service.findAll(pageable);
		
		produtos.stream().forEach(p -> p.add(linkTo(methodOn(VendaController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<VendaVO>> pagedModel = assembler.toModel(produtos);
		
		return ResponseEntity.ok(pagedModel);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<VendaVO> findById(@PathVariable Long id) {
		
		VendaVO vendaVO = service.findById(id);
		vendaVO.add(linkTo(methodOn(VendaController.class).findById(id)).withSelfRel());
		
		return ResponseEntity.ok(vendaVO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody VendaVO vendaUpdate) {
		
		VendaVO vendaVO = service.update(id, vendaUpdate);
		
		vendaVO.add(linkTo(methodOn(VendaController.class).findById(vendaVO.getId())).withSelfRel());
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(vendaVO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
