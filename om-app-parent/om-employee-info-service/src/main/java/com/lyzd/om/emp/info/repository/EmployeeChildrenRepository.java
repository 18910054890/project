package com.lyzd.om.emp.info.repository;

import static com.google.common.collect.ImmutableMap.of;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.info.model.EmployeeChildren;
import com.lyzd.om.shared.jackson.DefaultObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeChildrenRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private DefaultObjectMapper objectMapper;
	
	public EmployeeChildren queryEmployeeChildrenById(String userId) {
		String sql = "SELECT JSON_CONTENT FROM my_children WHERE userId=:userId;";
		return jdbcTemplate.queryForObject(sql, of("userId", userId), employeeChildrenMapper());
	}

	private RowMapper<EmployeeChildren> employeeChildrenMapper() {
		return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), EmployeeChildren.class);
	}
	
	public void saveEmployeeChildree(EmployeeChildren employeeChildren) {
		String sql = "INSERT INTO my_children (ID,JSON_CONTENT) VALUES (:id,:json) "
				+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, String> paramMap = of("id", employeeChildren.getId(), "json", objectMapper.writeValueAsString(employeeChildren));
		jdbcTemplate.update(sql, paramMap);
	
	}
	
}
