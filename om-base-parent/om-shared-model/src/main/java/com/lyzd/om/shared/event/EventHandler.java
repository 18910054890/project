package com.lyzd.om.shared.event;

import com.lyzd.om.shared.annotation.ConsumerPointCut;

/**
 * @author Thinker
 *
 */
public interface EventHandler<E extends DomainEvent> {

	@ConsumerPointCut
	public void handleEvent(E event);
	
}
