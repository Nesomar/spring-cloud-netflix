package br.com.academy.pagamento.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class VendaVO extends RepresentationModel<VendaVO> implements Serializable {

	private static final long serialVersionUID = 1910371319940223570L;

	private Long id;
	
	@NotNull
	private LocalDate dataVenda;
	
	@NotNull
	private Double valorTotal;
	
	@Valid
	@Size(min = 1)
	private List<ProdutoVendaVO> produtos;
}
