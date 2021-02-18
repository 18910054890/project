package com.lyzd.om.spring.common.event.messaging.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.transaction.annotation.Transactional;

import com.lyzd.om.shared.event.DomainEvent;
import com.lyzd.om.shared.event.DomainEventSender;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Thinker
 *
 */
@Slf4j
public class RabbitDomainEventSender implements DomainEventSender {
    private RabbitTemplate rabbitTemplate;
    private OMRabbitProperties omRabbitProperties;
    
    


    public RabbitDomainEventSender() {
		super();
	}

	public RabbitDomainEventSender(MessageConverter messageConverter,
                                   OMRabbitProperties omRabbitProperties,
                                   RabbitTemplate rabbitTemplate) {
        this.omRabbitProperties = omRabbitProperties;
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setChannelTransacted(true);
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional(transactionManager = "rabbitTransactionManager")
    public void send(DomainEvent event) {
        String exchange = omRabbitProperties.getPublishx();
        String routingKey = event.getClass().getName();
        rabbitTemplate.convertAndSend(exchange, routingKey, event);
        log.debug("Send {}.", event);
    }

}
