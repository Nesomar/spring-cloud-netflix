package br.com.academy.crud.service.mapper;

import org.modelmapper.ModelMapper;

import br.com.academy.crud.entity.Produto;
import br.com.academy.crud.model.ProdutoVO;

public class ProdutoMapper {

	private static final ModelMapper modelMapper = new ModelMapper();
	
	private ProdutoMapper () {
		
	}
	
	public static Produto toEntity(ProdutoVO produtoVO) {
		return modelMapper.map(produtoVO, Produto.class);
	}
	
	public static ProdutoVO toModel(Produto produto) {
		return modelMapper.map(produto, ProdutoVO.class);
	}
}
