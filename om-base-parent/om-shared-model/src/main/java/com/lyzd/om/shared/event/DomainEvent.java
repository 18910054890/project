package com.lyzd.om.shared.event;

import com.lyzd.om.shared.utils.DateUtil;
import com.lyzd.om.shared.utils.UuidGenerator;

import lombok.Getter;


/**
 * Base DomainEvent for all entities that need events.
 * 
 * @author Thinker
 *
 */
@Getter
public abstract class DomainEvent {
    private String id = UuidGenerator.newUuid();
    private String createdAt = DateUtil.now(); //now();

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + id + "]";
    }
    

}
