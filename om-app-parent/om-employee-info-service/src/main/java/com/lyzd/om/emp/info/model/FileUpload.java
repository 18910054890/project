package com.lyzd.om.emp.info.model;

import java.beans.ConstructorProperties;

import com.lyzd.om.emp.info.sdk.event.FileCreatedEvent;
import com.lyzd.om.shared.model.BaseAggregate;
import com.lyzd.om.shared.utils.UuidGenerator;

import lombok.Getter;

/**
 * @author Thinker
 *
 */
@Getter
public class FileUpload extends BaseAggregate {

	private String fileName;

	/**
	 * @param filePath
	 */
	@ConstructorProperties({ "filePath" })
	public FileUpload(String filePath) {
		if (filePath != null) {
			fileName = filePath + UuidGenerator.newUuid() + ".xlsx";
		} else {
			fileName = UuidGenerator.newUuid() + ".xlsx";
		}
		
		super.raiseEvent(new FileCreatedEvent(fileName));

	}
}
