package br.com.academy.pagemento.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.pagemento.entity.ProdutoVenda;
import br.com.academy.pagemento.repository.ProdutoVendaRepository;

@Service
public class ProdutoVendaService {

	@Autowired
	private ProdutoVendaRepository repository;
	
	@Transactional
	public ProdutoVenda create (ProdutoVenda produtoVenda) {
		return repository.save(produtoVenda);
	}
}
