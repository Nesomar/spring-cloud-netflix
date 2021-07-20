package br.com.academy.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.academy.crud.model.ProdutoVO;
import br.com.academy.crud.repository.ProdutoRepository;
import br.com.academy.crud.service.exception.NotFoundException;
import br.com.academy.crud.service.mapper.ProdutoMapper;

@Service
public class ProdutoService {

	private static final String PRODUTO_NAO_LOCALIZADO = "Produto %s não localizado!";
	
	@Autowired
	private ProdutoRepository repository; 

	@Transactional
	public ProdutoVO create(ProdutoVO produtoVO) {
		return ProdutoMapper.toModel(repository.save(ProdutoMapper.toEntity(produtoVO)));
	}
	
	public Page<ProdutoVO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(ProdutoMapper::toModel);
	}
	
	public ProdutoVO findById(Long id) {
		
		var optionalProduto = repository.findById(id);
		
		if(optionalProduto.isEmpty()) {
			throw new NotFoundException(String.format(PRODUTO_NAO_LOCALIZADO, id));
		}
		
		return ProdutoMapper.toModel(optionalProduto.get());
	}
	
	@Transactional
	public ProdutoVO update(Long id, ProdutoVO produtoVO) {
		
		var produto = repository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format(PRODUTO_NAO_LOCALIZADO, id)));
		
		produto.setEstoque(produtoVO.getEstoque());
		produto.setNome(produtoVO.getNome());
		produto.setPreco(produtoVO.getPreco());
		
		return ProdutoMapper.toModel(repository.save(produto));
	}
	
	@Transactional
	public void delete(Long id) {
		var produto = repository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format(PRODUTO_NAO_LOCALIZADO, id)));
		repository.delete(produto);
	}
}
