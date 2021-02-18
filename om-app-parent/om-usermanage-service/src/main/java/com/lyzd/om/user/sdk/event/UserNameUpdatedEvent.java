package com.lyzd.om.user.sdk.event;

import lombok.Getter;

import java.beans.ConstructorProperties;

/**
 * @author Thinker
 *
 */
@Getter
public class UserNameUpdatedEvent extends UserEvent {
    private String oldName;
    private String newName;

    @ConstructorProperties({"userId", "oldName", "newName"})
    public UserNameUpdatedEvent(String userId, String oldName, String newName) {
        super(userId);
        this.oldName = oldName;
        this.newName = newName;
    }

}
