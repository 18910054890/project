package com.lyzd.om.user.sdk.event;

import com.lyzd.om.shared.event.DomainEvent;

import lombok.Getter;

/**
 * @author Thinker
 *
 */
@Getter
public abstract class UserEvent extends DomainEvent {
	
    private String userId;

    protected UserEvent(String userId) {
        this.userId = userId;
    }

}
