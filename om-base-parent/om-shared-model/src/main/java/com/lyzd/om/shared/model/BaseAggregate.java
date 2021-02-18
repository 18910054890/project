package com.lyzd.om.shared.model;


import java.util.List;

import com.lyzd.om.shared.event.DomainEvent;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author Thinker
 *
 */
public abstract class BaseAggregate{
	
    private transient List<DomainEvent> events;
    
    protected final void raiseEvent(DomainEvent event) {
        getEvents().add(event);
    }

    final void clearEvents() {
        getEvents().clear();
    }

    final List<DomainEvent> getEvents() {
        if (events == null) {
            events = newArrayList();
        }
        return events;
    }

}
