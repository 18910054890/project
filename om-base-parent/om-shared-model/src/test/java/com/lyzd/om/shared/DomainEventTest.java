package com.lyzd.om.shared;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.lyzd.om.shared.event.DomainEvent;

/**
 * unit test
 * 
 * @author Thinker
 *
 */
class DomainEventTest {

    @Test
    public void shouldCreateEvent() {
        DomainEvent domainEvent = new DomainEvent() {
        };

        assertNotNull(domainEvent);
    }

}