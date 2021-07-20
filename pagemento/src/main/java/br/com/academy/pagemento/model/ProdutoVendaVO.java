package br.com.academy.pagemento.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class ProdutoVendaVO extends RepresentationModel<ProdutoVendaVO> implements Serializable {

	private static final long serialVersionUID = -3071951493464965351L;
	
	private Long id;
	
	@NotNull
	private Long idProduto;
	
	@NotNull
	private Integer quantidade;
	
}
