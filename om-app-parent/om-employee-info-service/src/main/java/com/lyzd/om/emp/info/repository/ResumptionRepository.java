package com.lyzd.om.emp.info.repository;
import static com.google.common.collect.ImmutableMap.of;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.info.model.Employee;
import com.lyzd.om.emp.info.model.Resumption;
import com.lyzd.om.shared.jackson.DefaultObjectMapper;
import com.lyzd.om.shared.model.BaseRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ResumptionRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private DefaultObjectMapper objectMapper;
	
	public Resumption employeeSumptionById(String userId) {
		String sql = "SELECT JSON_CONTENT FROM my_resumption WHERE userId=:userId;";
		return jdbcTemplate.queryForObject(sql, of("userId", userId), resumptionMapper());
	}

	private RowMapper<Resumption> resumptionMapper() {
		return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), Resumption.class);
	}
	
	
	public void saveResumption(Resumption resumption) {
		String sql = "INSERT INTO my_resumption (ID,JSON_CONTENT) VALUES (:id,:json) "
				+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, Object> paramMap = of("id", resumption.getId(), "json",
				objectMapper.writeValueAsString(resumption));
		jdbcTemplate.update(sql, paramMap);
	}

	
}
