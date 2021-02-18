package com.lyzd.om.shared.event;

import java.util.List;

/**
 * Responsible for persisting DomainEvents.
 * 
 * @author Thinker
 *
 */
public interface DomainEventDao {
	
    void save(List<DomainEvent> events);

    void delete(String eventId);

    DomainEvent get(String eventId);

    List<DomainEvent> nextPublishBatch(int size);

    void markAsPublished(String eventId);

    void markAsPublishFailed(String eventId);

    void deleteAll();

}
