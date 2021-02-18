package com.lyzd.om.shared.event;

/**
 * publish DomainEvents that maybe come from DataBase or files.
 * 
 * @author Thinker
 *
 */
public interface DomainEventPublisher {
	
    void publishNextBatch();

    void publishNextBatch(int size);

    void forcePublish(String eventId);
}
