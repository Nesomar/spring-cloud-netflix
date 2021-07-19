package br.com.academy.crud.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProdutoVO implements Serializable {

	private static final long serialVersionUID = 3319604504696036083L;

	private Long id;
	
	private String nome;
	
	private Integer estoque;
	
	private Double preco;
}
