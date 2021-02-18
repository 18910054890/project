package com.lyzd.om.emp.file.service;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author Thinker
 *
 */
public class FileInfoRowMapper implements RowMapper<FileInfo>{

	
	
	private boolean needFileContent = true;
	
	public FileInfoRowMapper() {
		super();
		this.needFileContent = true;
	}

	public FileInfoRowMapper(boolean needFileContent) {
		super();
		this.needFileContent = needFileContent;
	}

	
	
	@Override
	public FileInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		FileInfo fileInfo = new FileInfo();
		
		fileInfo.setId((rs.getString("id")));
		fileInfo.setUserId(rs.getString("userId"));
		fileInfo.setFileName(rs.getString("fileName"));
		fileInfo.setFileType(rs.getString("fileType"));
		//fileInfo.setCreatedAt(rs.getString("createdAt"));
		fileInfo.setComment(rs.getString("comment"));
		
		if(needFileContent) {
			fileInfo.setContent(rs.getBytes("content"));
		}
		
		
		return fileInfo;
	}

	

}
