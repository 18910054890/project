package com.lyzd.om.user.sdk.event;

import org.springframework.stereotype.Component;

import com.lyzd.om.shared.annotation.ConsumerPointCut;
import com.lyzd.om.shared.event.EventHandler;

/**
 * @author Thinker
 *
 */
@Component
public class UserNameUpdateHandler implements EventHandler <UserNameUpdatedEvent>{

	
	@ConsumerPointCut
	public void handleEvent(UserNameUpdatedEvent event) {
		System.out.println(event);
	}

	
}
