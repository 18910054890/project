package com.lyzd.om.user.sdk.event;

import lombok.Getter;

import java.beans.ConstructorProperties;
import java.time.Instant;

/**
 * @author Thinker
 *
 */
@Getter
public class UserCreatedEvent extends UserEvent {
    private String name;
    private String createdAt;


    @ConstructorProperties({"userId", "name", "createdAt"})
    public UserCreatedEvent(String userId, String name, String createdAt) {
        super(userId);
        this.name = name;
        this.createdAt = createdAt;
    }
}
