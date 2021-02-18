package com.lyzd.om.emp.info.repository;

import static com.google.common.collect.ImmutableMap.of;
import static com.lyzd.om.shared.utils.UuidGenerator.newUuid;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.info.model.WorkExperience;
import com.lyzd.om.shared.jackson.DefaultObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WorkExperienceRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private DefaultObjectMapper objectMapper;
	
	
	/**
	 * 保存新增的外部工作经历
	 * @param workExperience
	 */
	public void saveNewWorkExperience(WorkExperience workExperience) {
		String sql = "INSERT INTO my_workexperience (ID,JSON_CONTENT) VALUES (:id,:json) "
					+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, String> paramMap = of("id",newUuid(), "json",
					objectMapper.writeValueAsString(workExperience));
		jdbcTemplate.update(sql, paramMap);
		log.info("新增外部工作经验保存成功！");
	}
	
	public void saveWorkExperience(WorkExperience workExperience) {
		String sql = "INSERT INTO my_workexperience (ID,JSON_CONTENT) VALUES (:id,:json) "
				+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, String> paramMap = of("id", workExperience.getId(), "json",
				objectMapper.writeValueAsString(workExperience));
		jdbcTemplate.update(sql, paramMap);
		
	}
}
