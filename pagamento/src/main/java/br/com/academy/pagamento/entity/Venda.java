package br.com.academy.pagamento.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "venda")
@Data
@NoArgsConstructor
public class Venda implements Serializable {
	
	private static final long serialVersionUID = -3793838792688564894L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "data_venda", nullable = false)
	private LocalDate dataVenda;
	
	@Column(name = "valor_total", nullable = false, length = 10)
	private Double valorTotal;
	
	@Setter(value = AccessLevel.PRIVATE)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", cascade = CascadeType.REFRESH, orphanRemoval = true)
	private List<ProdutoVenda> produtos = new ArrayList<>();
	
	public void adicionarProdutos(List<ProdutoVenda> produtos) {
		produtos.forEach(pv -> pv.setVenda(this));
	}
	
	public void adicionarProduto(ProdutoVenda produto) {
		produto.setVenda(this);
		produtos.add(produto);
	}
	
	public void removerProdutos() {
		produtos.forEach(pv -> pv.setVenda(this));
		produtos.clear();
	}

}
