package com.lyzd.om.emp.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.stereotype.Component;

import com.lyzd.om.shared.exception.MyException;
import com.lyzd.om.shared.jackson.DefaultObjectMapper;
import com.lyzd.om.shared.model.BaseRepository;
import com.lyzd.om.spring.common.dto.ResultCode;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;

/**
 * @author Thinker
 *
 */
@Component
public class FileInfoRepository extends BaseRepository<FileInfo> {

	private NamedParameterJdbcTemplate jdbcTemplate;

	private DefaultObjectMapper objectMapper;

	@Autowired
	public FileInfoRepository(NamedParameterJdbcTemplate jdbcTemplate, DefaultObjectMapper objectMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.objectMapper = objectMapper;
	}

	@Override
	protected void doSave(FileInfo fileInfo) {
		String sql = "INSERT INTO My_FileInfo (id, userId, fileName, fileType, content, comment,createdAt) "
				+ "VALUES (:id, :userId, :fileName, :fileType, :content, :comment, :createdAt) "
				+ "ON DUPLICATE KEY UPDATE content=:content;";
		// Map<String, String> paramMap = of("userId", fileInfo.getUserId(), "fileType",
		// fileInfo.getFileType(),
		// "content", "");
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("id", fileInfo.getId());
		paramMap.addValue("userId", fileInfo.getUserId());
		paramMap.addValue("fileType", fileInfo.getFileType());
		paramMap.addValue("fileName", fileInfo.getFileName());
		paramMap.addValue("content", new SqlLobValue(new ByteArrayInputStream(fileInfo.getContent()),
				fileInfo.getContent().length, new DefaultLobHandler()), Types.BLOB);
		paramMap.addValue("comment", fileInfo.getComment());
		paramMap.addValue("createdAt", fileInfo.getCreatedAt());

		jdbcTemplate.update(sql, paramMap);
	}

	/**
	 * Query file info
	 * @param userId  must input
	 * @param fileType  optional 
	 * @return
	 */
	public List<FileInfoRepresentation> queryFileInfo(String userId, String fileType) {
		
		try {
			StringBuilder sql = new StringBuilder("SELECT id, userId, fileName, fileType, comment FROM My_FileInfo WHERE userId=:userId ");
			MapSqlParameterSource paramMap = new MapSqlParameterSource();
			paramMap.addValue("userId", userId);
			
			if(fileType != null && fileType.trim().length() > 0 ) {
				paramMap.addValue("fileType", fileType);
				sql.append(" and fileType=:fileType ");
			}
			sql.append(" order by  createdAt desc;"); 
			
			return jdbcTemplate.query(sql.toString(), paramMap, new FileInfoRepresentationRowMapper());
			
			
			
		} catch (EmptyResultDataAccessException e) {
			throw new MyException(ResultCode.NOT_FOUND, "未找到数据: " + userId + " : " + fileType);
		}
       
		
	}

	public FileInfo byId(String id) {
		try {
			String sql = "SELECT id, userId, fileName, fileType, content, comment FROM My_FileInfo WHERE id=:id;";
			return jdbcTemplate.queryForObject(sql, of("id", id), new FileInfoRowMapper());
		} catch (EmptyResultDataAccessException e) {
			throw new MyException(ResultCode.NOT_FOUND, "未找到数据: " + id);
		}

	}
	
	public void deleteById(String id) {
		String sql = "delete FROM My_FileInfo WHERE id=:id;";
		jdbcTemplate.update(sql, of("id", id));
    }

}
