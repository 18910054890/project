package com.lyzd.om.emp.info.sdk.event;

import com.lyzd.om.emp.info.sdk.commond.CreateEducationCommand;
import com.lyzd.om.shared.event.DomainEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Thinker
 *
 */
//@Getter
@Data
@NoArgsConstructor
public class FileCreatedEvent extends DomainEvent {
	
    private String fileName;

    public FileCreatedEvent(String fileName) {
    	this.fileName = fileName;
    }
}
