package com.lyzd.om.emp.info.sdk.event;

import java.beans.ConstructorProperties;
import java.time.Instant;


public class EmployeeCreatedEvent extends EmployEvent{
	 private String name;
	    private Instant createdAt;


	    @ConstructorProperties({"userId", "name", "createdAt"})
	    public EmployeeCreatedEvent(String userId,  String name, Instant createdAt) {
	        super(userId);
	        this.name = name;
	        this.createdAt = createdAt;
	    }
}
