package com.lyzd.om.emp.info.repository;

import static com.google.common.collect.ImmutableMap.of;
import static com.lyzd.om.shared.utils.UuidGenerator.newUuid;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.info.model.LyWorkExperience;
import com.lyzd.om.shared.jackson.DefaultObjectMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class LyWorkExperienceRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private DefaultObjectMapper objectMapper;

	
	
	public LyWorkExperience lyWorkExperienceById(String id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("id", id);
		String sql = "SELECT JSON_CONTENT FROM my_lyworkexperience WHERE id=:id;";
		return jdbcTemplate.queryForObject(sql, of("id", id), lyExperienceMapper());
	}
	
	
	private RowMapper<LyWorkExperience> lyExperienceMapper() {
		return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), LyWorkExperience.class);
	}
	
	
	public void saveLyWorkExperience(LyWorkExperience lyWorkExperience){
		String sql = "INSERT INTO my_lyworkexperience (ID,JSON_CONTENT) VALUES (:id,:json)"+   
			         "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, Object> paramMap = of("id", lyWorkExperience.getId(), "json",
				objectMapper.writeValueAsString(lyWorkExperience));
		jdbcTemplate.update(sql, paramMap);
		log.info("新增内部工作经验保存成功！");
	}
	
	
	public void deleteLyWorkExperienceByUserId(String userId) {
		String sql = "delete FROM my_lyworkexperience WHERE userId=:userId;";
		jdbcTemplate.update(sql, of("userId",userId));
    }

	
}
