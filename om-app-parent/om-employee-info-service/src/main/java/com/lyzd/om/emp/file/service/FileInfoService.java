package com.lyzd.om.emp.file.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import cn.hutool.core.io.IoUtil;

/**
 * PDF-File related services.
 * @author Thinker
 *
 */
@Component
public class FileInfoService {
	
	@Autowired
	private FileInfoRepository fileInfoRepository;
	
	/**
	 *  Save a PDF file.
	 * @param file
	 * @param userId
	 * @param fileType
	 * @return
	 * @throws IOException
	 */
	public FileInfoRepresentation save(MultipartFile file, String userId, String fileType) throws IOException {
		String fileOrigName = file.getOriginalFilename();
		if (!fileOrigName.contains(".")) {
			throw new IllegalArgumentException("缺少后缀名");
		}
		
		//String fileContent = Base64.encode(file.getInputStream());
		
		FileInfo fi = new FileInfo(userId, fileOrigName, fileType, IoUtil.readBytes(file.getInputStream()));
		FileInfoRepresentation fileInfoRepresentation = fi.toFileInfoRepresentation();
		fileInfoRepository.save(fi);
		return fileInfoRepresentation;
	}
	
	/**
	 * Query user's uploaded files, for example, users' ID card, labor contract ...
	 * @param userId
	 * @param fileType  optional, if fileType is null, will list user's all uploaded files.
	 * @return
	 */
	public List<FileInfoRepresentation> queryFileInfo(String userId, String fileType) {
		return fileInfoRepository.queryFileInfo(userId, fileType);
	}
	
	/**
	 * Query a file info by id.
	 * @param id
	 * @return
	 */
	public FileInfo byId(String id) {
		return fileInfoRepository.byId(id);
	}
	
	/**
	 * Delete a file by id.
	 * @param id
	 */
	public void deleteById(String id) {
		fileInfoRepository.deleteById(id);
	}
}
