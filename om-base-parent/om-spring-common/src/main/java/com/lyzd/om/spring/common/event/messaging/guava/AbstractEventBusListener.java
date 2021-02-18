package com.lyzd.om.spring.common.event.messaging.guava;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.eventbus.EventBus;

/**
 * Concrete business event listener should extend  this class, 
 * and write event handler that use @Subscribe annotation.
 *  method.
 * 
 * @author Thinker
 *
 */
public abstract class AbstractEventBusListener { //<E extends DomainEvent> {

	@Autowired
	protected EventBus eventBus;

	@PostConstruct
	public void init() {
		eventBus.register(this);
	}

	/**
	 * invoke application service or domain service
	 * 
	 * @param event
	 */
	//@Subscribe
	//public abstract void handleEvent(E event);

}
