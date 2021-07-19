package br.com.academy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.academy.model.ProdutoVO;
import br.com.academy.repository.ProdutoRepository;
import br.com.academy.service.exception.NotFoundException;
import br.com.academy.service.mapper.ProdutoMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

	private static final String PRODUTO_NAO_LOCALIZADO = "Produto %s não localizado!";
	
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
	public void update(Long id) {
		var produto = repository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format(PRODUTO_NAO_LOCALIZADO, id)));
		repository.save(produto);
	}
	
	@Transactional
	public void delete(Long id) {
		var produto = repository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format(PRODUTO_NAO_LOCALIZADO, id)));
		repository.delete(produto);
	}
}
