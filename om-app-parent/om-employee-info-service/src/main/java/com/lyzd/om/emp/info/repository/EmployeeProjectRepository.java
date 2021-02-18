package com.lyzd.om.emp.info.repository;

import static com.google.common.collect.ImmutableMap.of;
import static com.lyzd.om.shared.utils.UuidGenerator.newUuid;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.info.model.EmployeeProject;
import com.lyzd.om.shared.jackson.DefaultObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeProjectRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private DefaultObjectMapper objectMapper;
	
	
	/**
	 * 保存新增的外部项目经历
	 * @param workExperience
	 */
	public void saveNewEmployeeProject(EmployeeProject employeeProject) {
		String sql = "INSERT INTO my_project (ID,JSON_CONTENT) VALUES (:id,:json) "
					+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, String> paramMap = of("id",newUuid(), "json",
					objectMapper.writeValueAsString(employeeProject));
		jdbcTemplate.update(sql, paramMap);
		log.info("新增外部项目经验保存成功！");
	}
	
	public void saveEmployeeProject(EmployeeProject employeeProject) {
		String sql = "INSERT INTO my_project (ID,JSON_CONTENT) VALUES (:id,:json) "
					+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, String> paramMap = of("id",employeeProject.getId(), "json",
					objectMapper.writeValueAsString(employeeProject));
		jdbcTemplate.update(sql, paramMap);
		log.info("项目经验保存成功！");
	}
	
	
}
