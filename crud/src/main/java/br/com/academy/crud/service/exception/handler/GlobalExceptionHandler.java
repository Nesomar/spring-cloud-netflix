package br.com.academy.crud.service.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.academy.crud.model.MensagemErrorVO;
import br.com.academy.crud.service.exception.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String RECURSO_NAO_LOCALIZADO_EM_NOSSSOS_REGISTROS = "Recurso não localizado em nosssos registros.";

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<MensagemErrorVO> handleNotFoundException(NotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(MensagemErrorVO
				.builder()
				.code(HttpStatus.NOT_FOUND.value())
				.status(HttpStatus.NOT_FOUND.name())
				.mensagem(RECURSO_NAO_LOCALIZADO_EM_NOSSSOS_REGISTROS)
				.detalhe(ex.getMessage())
				.build());
	}
}
