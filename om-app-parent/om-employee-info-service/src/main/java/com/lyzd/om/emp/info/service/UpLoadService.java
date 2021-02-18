package com.lyzd.om.emp.info.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.info.model.FileUpload;
import com.lyzd.om.emp.info.sdk.commond.FileUploadCommand;
import com.lyzd.om.emp.info.sdk.event.FileCreatedEvent;
import com.lyzd.om.shared.model.BaseRepository;

/**
 * @author Thinker
 *
 */
@Component
public class UpLoadService {
	
	// 存储文件的服务器路径
	@Value("${path.filePath}")
	private String filePath;
	
	

	private BaseRepository<FileUpload> repository = new BaseRepository<FileUpload>() {
		@Override
		protected void doSave(FileUpload fileUpload) {
			// Nothing to persist.
		}
	};

	public void saveFile(FileUploadCommand fileUploadCommand) throws IOException {
		FileUpload fileUpload = new FileUpload(filePath);
		
		InputStream inputStream = fileUploadCommand.getInputStream();
		String fileName = fileUpload.getFileName();

		// TODO ..

		FileOutputStream fos = new FileOutputStream(fileName);
		try {
			byte[] b = new byte[1024];
			int length;
			try {
				while ((length = inputStream.read(b)) != -1) {
					fos.write(b, 0, length);
				}
				repository.save(fileUpload);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			fos.close();
			inputStream.close();
		}

		FileCreatedEvent fileCreatedEvent = new FileCreatedEvent();
		fileCreatedEvent.setFileName(fileName);
	}
 
}
