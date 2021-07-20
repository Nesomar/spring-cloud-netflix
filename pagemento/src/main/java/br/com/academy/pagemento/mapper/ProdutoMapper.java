package br.com.academy.pagemento.mapper;

import org.modelmapper.ModelMapper;

import br.com.academy.pagemento.entity.Produto;
import br.com.academy.pagemento.model.ProdutoVO;

public class ProdutoMapper {

	private static ModelMapper modelMapper = new ModelMapper();

	private ProdutoMapper() {

	}

	public static ProdutoVO toModel(Produto produto) {
		return modelMapper.map(produto, ProdutoVO.class);
	}

	public static Produto toEntity(ProdutoVO produtoVO) {
		return modelMapper.map(produtoVO, Produto.class);
	}
}
