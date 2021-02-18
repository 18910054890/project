package com.lyzd.om.shared.event;

/**
 * @author Thinker
 *
 */
public interface DomainEventConsumeRecorder {

    boolean record(DomainEvent event);

    void deleteAll();
}
