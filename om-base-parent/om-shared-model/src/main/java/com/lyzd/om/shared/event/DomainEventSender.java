package com.lyzd.om.shared.event;

/**
 * Responsible for sending DomainEvents.
 * 
 * @author Thinker
 *
 */
public interface DomainEventSender {
	
    void send(DomainEvent event);
}
