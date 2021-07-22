package br.com.academy.crud.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.academy.crud.model.ProdutoVO;

@Component
public class ProdutoSendMessage {

	@Value("${crud.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${crud.rabbitmq.routingkey}")
	private String routingkey;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(ProdutoVO produtoVO) {
		rabbitTemplate.convertAndSend(exchange, routingkey, produtoVO);
	}
	
}
