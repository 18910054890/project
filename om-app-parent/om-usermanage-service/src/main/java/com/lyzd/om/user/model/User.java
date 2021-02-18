package com.lyzd.om.user.model;


import static com.lyzd.om.shared.utils.UuidGenerator.newUuid;

import com.lyzd.om.shared.model.BaseAggregate;
import com.lyzd.om.shared.utils.DateUtil;
import com.lyzd.om.user.sdk.event.UserCreatedEvent;
import com.lyzd.om.user.sdk.event.UserNameUpdatedEvent;
import com.lyzd.om.user.sdk.representation.UserRepresentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * @author Thinker
 *
 */
@Builder
@Getter
//@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseAggregate {
	
    private String id;
    private String name;
    private int age;
    private String createdAt;
    
    // Other properties ...

    public static User create(String name, int age) {
    	
        User user = User.builder()
                .id(newUuid())
                .name(name).age(age).createdAt(DateUtil.now())
                .build();
        
        user.raiseEvent(new UserCreatedEvent(user.getId(), name,  user.getCreatedAt()));
        
        
        return user;
    }

    public UserRepresentation toRepresentation() {
    	
//    	UserRepresentation target = new UserRepresentation();
//      BeanUtils.copyProperties(this, target);
        
        return new UserRepresentation(id, name);
    }
    
    public void updateName(String newName) {
        raiseEvent(new UserNameUpdatedEvent(this.getId(), name, newName));
        this.name = newName;
    }
}
