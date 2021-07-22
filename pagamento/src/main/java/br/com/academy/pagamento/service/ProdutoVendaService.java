package br.com.academy.pagamento.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.pagamento.entity.ProdutoVenda;
import br.com.academy.pagamento.repository.ProdutoVendaRepository;

@Service
public class ProdutoVendaService {

	@Autowired
	private ProdutoVendaRepository repository;
	
	@Transactional
	public ProdutoVenda create (ProdutoVenda produtoVenda) {
		return repository.save(produtoVenda);
	}
}
