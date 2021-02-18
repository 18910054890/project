package com.lyzd.om.emp.info.repository;

import static com.google.common.collect.ImmutableMap.of;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.info.model.ContractInfo;
import com.lyzd.om.emp.info.model.Education;
import com.lyzd.om.shared.jackson.DefaultObjectMapper;

@Component
public class ContractInfoRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	@Autowired
	private DefaultObjectMapper objectMapper;

	public ContractInfo employeeContractByUserId(String userId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		parameters.addValue("limit", 1);
		parameters.addValue("offset", 0);
		String sql = "SELECT JSON_CONTENT FROM my_contractinfo WHERE userId=:userId  order by createdAt desc limit :limit offset :offset;";
//		return jdbcTemplate.query(sql, parameters,
//				(rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), ContractInfo.class));
		return jdbcTemplate.queryForObject(sql, parameters, contractInfoMapper());
	}

	private RowMapper<ContractInfo> contractInfoMapper() {
		return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), ContractInfo.class);
	}

	public void saveContractById(ContractInfo contractInfo) {
		String sql = "INSERT INTO my_contractinfo (ID,JSON_CONTENT) VALUES (:id,:json) "
				+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		Map<String, String> paramMap = of("id", contractInfo.getId(), "json",
				objectMapper.writeValueAsString(contractInfo));
		jdbcTemplate.update(sql, paramMap);
	}

}
