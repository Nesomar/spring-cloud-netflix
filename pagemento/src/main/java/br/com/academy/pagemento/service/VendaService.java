package br.com.academy.pagemento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.academy.pagemento.entity.ProdutoVenda;
import br.com.academy.pagemento.entity.Venda;
import br.com.academy.pagemento.mapper.ProdutoVendaMapper;
import br.com.academy.pagemento.mapper.VendaMapper;
import br.com.academy.pagemento.model.VendaVO;
import br.com.academy.pagemento.repository.VendaRepository;
import br.com.academy.pagemento.service.exception.NotFoundException;

@Service
public class VendaService {

	private static final String VENDA_NAO_LOCALIZADO = "Venda %s não localizada!";
	
	@Autowired
	private VendaRepository repository;
	
	@Autowired
	private ProdutoVendaService produtoVendaService;
	
	@Transactional
	public VendaVO create(VendaVO vendaVO) {
		
		Venda venda = repository.save(VendaMapper.toEntity(vendaVO));
		
		vendaVO.getProdutos().forEach(p -> {
			ProdutoVenda pv = ProdutoVendaMapper.toEntity(p);
			venda.adicionarProduto(produtoVendaService.create(pv));
		});
		
		return VendaMapper.toModel(venda);
	}
	
	public Page<VendaVO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(VendaMapper::toModel);
	}
	
	public VendaVO findById(Long id) {
		
		var optionalVenda = repository.findById(id);
		
		if(optionalVenda.isEmpty()) {
			throw new NotFoundException(String.format(VENDA_NAO_LOCALIZADO, id));
		}
		
		return VendaMapper.toModel(optionalVenda.get());
	}
	
	@Transactional
	public VendaVO update(Long id, VendaVO vendaUpdate) {
		
		var venda = repository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format(VENDA_NAO_LOCALIZADO, id)));
		
		venda.setDataVenda(vendaUpdate.getDataVenda());
		venda.setValorTotal(vendaUpdate.getValorTotal());
		
		return VendaMapper.toModel(repository.save(venda));
	}
	
	@Transactional
	public void delete(Long id) {
		
		var venda = repository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format(VENDA_NAO_LOCALIZADO, id)));
		
		venda.removerProdutos();
		
		repository.delete(venda);
	}
}
