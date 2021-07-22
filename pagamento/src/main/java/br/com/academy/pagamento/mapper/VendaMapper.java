package br.com.academy.pagamento.mapper;

import org.modelmapper.ModelMapper;

import br.com.academy.pagamento.entity.Venda;
import br.com.academy.pagamento.model.VendaVO;

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
