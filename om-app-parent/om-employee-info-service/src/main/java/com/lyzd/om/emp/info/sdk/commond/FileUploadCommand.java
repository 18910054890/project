package com.lyzd.om.emp.info.sdk.commond;

import java.io.InputStream;

import lombok.Getter;

/**
 * @author Thinker
 *
 */
@Getter
public class FileUploadCommand {
	
	
	private InputStream inputStream;

	public FileUploadCommand(InputStream inputStream) {
		super();
		this.inputStream = inputStream;
	}
	
}
