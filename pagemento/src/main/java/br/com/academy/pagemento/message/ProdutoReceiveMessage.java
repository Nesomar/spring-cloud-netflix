package br.com.academy.pagemento.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.academy.pagemento.mapper.ProdutoMapper;
import br.com.academy.pagemento.model.ProdutoVO;
import br.com.academy.pagemento.repository.ProdutoRepository;

@Component
public class ProdutoReceiveMessage {

	@Autowired
	private ProdutoRepository repository;
	
	@RabbitListener(queues = {"${crud.rabbitmq.queue}"})
	public void receive(@Payload ProdutoVO produtoVO) {
		repository.save(ProdutoMapper.toEntity(produtoVO));
	}
}
