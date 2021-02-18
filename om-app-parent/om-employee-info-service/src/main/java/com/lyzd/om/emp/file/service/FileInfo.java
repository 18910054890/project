package com.lyzd.om.emp.file.service;

import static com.lyzd.om.shared.utils.UuidGenerator.newUuid;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.lyzd.om.shared.model.BaseAggregate;
import com.lyzd.om.shared.utils.DateUtil;

import lombok.Data;

/**
 * @author Thinker
 *
 */
@Data
public class FileInfo extends BaseAggregate{
	
	private String id;
	
	private String userId;
	
	/**1: 身份证；2: 劳动合同；3: 保密协议；4: 学历证书；5: 学位证书。。。**/
	private String fileType;
	
	private String fileName;
	
	/**文件内容，Base64**/
	private byte[] content;
	
	private String comment;
	
	private String createdAt;
	
	private Map<String, String> fileTypeMap;
	
	public FileInfo() {
		super();
		this.fileTypeMap = new HashMap<String, String> ();
		fileTypeMap.put("1", "身份证");
		fileTypeMap.put("2", "劳动合同");
		fileTypeMap.put("3", "保密协议");
		fileTypeMap.put("4", "学历证书");
		fileTypeMap.put("5", "学位证书");
		fileTypeMap.put("6", "资质证书");
		fileTypeMap.put("7", "一寸照片");
	}

	/**
	 * @param userId
	 * @param fileName
	 * @param fileType
	 * @param content
	 */
	public FileInfo(String userId, String fileName, String fileType, byte[] content) {
		this();
		this.id = newUuid();
		this.userId = userId;
		this.fileName = fileName;
		this.fileType = fileType;
		this.content = content;
		this.comment = fileTypeMap.get(fileType);
		this.createdAt = DateUtil.now();
	}
	
	public FileInfoRepresentation toFileInfoRepresentation() {
		FileInfoRepresentation target = new FileInfoRepresentation();
		BeanUtils.copyProperties(this, target);
		return target;
	}
}
