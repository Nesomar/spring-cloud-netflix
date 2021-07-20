package br.com.academy.crud.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MensagemErrorVO implements Serializable {

	private static final long serialVersionUID = 1368277746709962506L;
	
	private String mensagem;
	private String status;
	private Integer code;
	private String detalhe;

}
