package br.com.academy.pagemento.mapper;

import org.modelmapper.ModelMapper;

import br.com.academy.pagemento.entity.Venda;
import br.com.academy.pagemento.model.VendaVO;

public class VendaMapper {

	private static ModelMapper modelMapper = new ModelMapper();
	
	private VendaMapper() {
		
	}
	
	public static VendaVO toModel(Venda venda) {
		return modelMapper.map(venda, VendaVO.class);
	}
	
	public static Venda toEntity(VendaVO vendaVO) {
		return modelMapper.map(vendaVO, Venda.class);
	}
}
