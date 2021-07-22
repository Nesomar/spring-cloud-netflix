package br.com.academy.pagamento.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.academy.pagamento.mapper.ProdutoMapper;
import br.com.academy.pagamento.model.ProdutoVO;
import br.com.academy.pagamento.repository.ProdutoRepository;

@Component
public class ProdutoReceiveMessage {

	@Autowired
	private ProdutoRepository repository;
	
	@RabbitListener(queues = {"${crud.rabbitmq.queue}"})
	public void receive(@Payload ProdutoVO produtoVO) {
		repository.save(ProdutoMapper.toEntity(produtoVO));
	}
}
