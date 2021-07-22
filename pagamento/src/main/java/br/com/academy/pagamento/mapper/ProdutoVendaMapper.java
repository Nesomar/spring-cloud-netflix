package br.com.academy.pagamento.mapper;

import org.modelmapper.ModelMapper;

import br.com.academy.pagamento.entity.ProdutoVenda;
import br.com.academy.pagamento.model.ProdutoVendaVO;

public class ProdutoVendaMapper {

	private static ModelMapper modelMapper = new ModelMapper();
	
	private ProdutoVendaMapper() {
		
	}
	
	public static ProdutoVendaVO toModel(ProdutoVenda produtoVenda) {
		return modelMapper.map(produtoVenda, ProdutoVendaVO.class);
	}
	
	public static ProdutoVenda toEntity(ProdutoVendaVO produtoVendaVO) {
		return modelMapper.map(produtoVendaVO, ProdutoVenda.class);
	}
}
