package com.lyzd.om.emp.info.repository;

import static com.google.common.collect.ImmutableMap.of;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.info.model.EmployeeProject;
import com.lyzd.om.emp.info.model.WorkExperience;
import com.lyzd.om.shared.jackson.DefaultObjectMapper;

/**
 * Inner projects experience.
 * @author Thinker
 *
 */
@Component
public class InnerProjectRepository {


	private NamedParameterJdbcTemplate jdbcTemplate;

	private DefaultObjectMapper objectMapper;

	@Autowired
	public InnerProjectRepository(NamedParameterJdbcTemplate jdbcTemplate, DefaultObjectMapper objectMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.objectMapper = objectMapper;
	}

	public List<EmployeeProject> queryInnerProject(String userId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		List<EmployeeProject> projectList = null;
		String sql = "SELECT JSON_CONTENT FROM my_InnerProject WHERE userId=:userId;";
		projectList = jdbcTemplate.query(sql, parameters,
				(rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), EmployeeProject.class));
		return projectList;

	}
	
	
	public EmployeeProject queryInnerProjectById(String id){
		String sql = "SELECT JSON_CONTENT FROM my_InnerProject WHERE id=:id;";
		return jdbcTemplate.queryForObject(sql, of("id", id),employeeProjectMapper());
		

	}
	private RowMapper<EmployeeProject> employeeProjectMapper() {
		return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), EmployeeProject.class);
	}

	
	public void saveInnerProject(EmployeeProject employeeProject) {
		
			String sql = "INSERT INTO my_InnerProject (ID,JSON_CONTENT) VALUES (:id,:json) "
						+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
			Map<String, String> paramMap = of("id",employeeProject.getId(), "json",
						objectMapper.writeValueAsString(employeeProject));
			jdbcTemplate.update(sql, paramMap);	
	}
	

	
	public void deleteInnerProjectByUserId(String userId) {
		String sql = "delete FROM my_InnerProject WHERE userId=:userId;";
		jdbcTemplate.update(sql, of("userId", userId));
    }

	
}
