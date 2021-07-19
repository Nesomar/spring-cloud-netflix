package br.com.academy.crud.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.academy.crud.model.ProdutoVO;
import br.com.academy.crud.service.ProdutoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/produtos")
public class ProdutoController {

	private ProdutoService service;
	
	@PostMapping
	public ResponseEntity<ProdutoVO> create(ProdutoVO produtoVO) {
		return null;
	}
	
	@GetMapping
	public ResponseEntity<Page<ProdutoVO>> findAll(Pageable pageable) {
		return null;
	}
	
	@GetMapping
	public ResponseEntity<ProdutoVO> findById(Long id) {
		return null;
	}
	
	@PatchMapping
	public ResponseEntity<?> update(Long id) {
		return null;
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(Long id) {
		return null;
	}
}
