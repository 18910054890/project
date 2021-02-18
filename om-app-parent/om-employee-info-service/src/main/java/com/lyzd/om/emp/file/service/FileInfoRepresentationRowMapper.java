package com.lyzd.om.emp.file.service;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author Thinker
 *
 */
public class FileInfoRepresentationRowMapper implements RowMapper<FileInfoRepresentation>{
	
	
	public FileInfoRepresentationRowMapper() {
		super();
	}

	@Override
	public FileInfoRepresentation mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		FileInfoRepresentation fileInfo = new FileInfoRepresentation();
		
		fileInfo.setId((rs.getString("id")));
		fileInfo.setFileName(rs.getString("fileName"));
		fileInfo.setFileType(rs.getString("fileType"));
		fileInfo.setComment(rs.getString("comment"));
		
		return fileInfo;
	}

	

}
