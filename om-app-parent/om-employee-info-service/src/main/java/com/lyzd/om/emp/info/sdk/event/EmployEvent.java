package com.lyzd.om.emp.info.sdk.event;

import com.lyzd.om.shared.event.DomainEvent;

import lombok.Getter;

@Getter
public abstract class EmployEvent extends DomainEvent {
	
    private String userId;

    protected EmployEvent(String userId) {
        this.userId = userId;
    }

}
