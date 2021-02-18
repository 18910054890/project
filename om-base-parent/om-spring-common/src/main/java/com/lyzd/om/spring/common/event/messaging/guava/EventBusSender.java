package com.lyzd.om.spring.common.event.messaging.guava;

import com.google.common.eventbus.EventBus;
import com.lyzd.om.shared.event.DomainEvent;
import com.lyzd.om.shared.event.DomainEventSender;

/**
 * Send events to EventBus
 * 
 * @author Thinker
 *
 */
public class EventBusSender implements DomainEventSender {

    EventBus eventBus;
    
    

	public EventBusSender(EventBus eventBus) {
		super();
		this.eventBus = eventBus;
	}



	@Override
	//@ConsumerPointCut
	public void send(DomainEvent event) {
		
		eventBus.post(event);
		
	}

}
