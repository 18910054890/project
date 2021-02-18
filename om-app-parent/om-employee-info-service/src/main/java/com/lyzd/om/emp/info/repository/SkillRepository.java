package com.lyzd.om.emp.info.repository;

import static com.google.common.collect.ImmutableMap.of;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.info.model.Skill;
import com.lyzd.om.shared.jackson.DefaultObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SkillRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private DefaultObjectMapper objectMapper;
	
	public void saveSkill(Skill skill) {
		String sql = "INSERT INTO my_skill (ID,JSON_CONTENT) VALUES (:id,:json) "
				+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, String> paramMap = of("id", skill.getId(), "json", objectMapper.writeValueAsString(skill));
		jdbcTemplate.update(sql, paramMap);
	}
	
	public Skill employeeSkillById(String userId) {
		String sql = "SELECT JSON_CONTENT FROM my_skill WHERE userId=:userId;";
		return jdbcTemplate.queryForObject(sql, of("userId", userId), skillMapper());
	}

	private RowMapper<Skill> skillMapper() {
		return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), Skill.class);
	}

	

	
}
