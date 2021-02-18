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
import com.lyzd.om.emp.info.model.Employee;
import com.lyzd.om.emp.info.model.EmployeeChildren;
import com.lyzd.om.emp.info.model.EmployeeProject;
import com.lyzd.om.emp.info.model.LyWorkExperience;
import com.lyzd.om.emp.info.model.MyEmployee;
import com.lyzd.om.emp.info.model.Resumption;
import com.lyzd.om.emp.info.model.Skill;
import com.lyzd.om.emp.info.model.WorkExperience;
import com.lyzd.om.emp.info.model.eductionNewType;
import com.lyzd.om.shared.jackson.DefaultObjectMapper;
import com.lyzd.om.shared.model.BaseRepository;

/**
 * Employee info edit repository.
 * 
 * @author Thinker
 *
 */
@Component
public class EmployeeInfoAuditRepository extends BaseRepository<Employee> {


	private NamedParameterJdbcTemplate jdbcTemplate;

	private DefaultObjectMapper objectMapper;

	@Autowired
	public EmployeeInfoAuditRepository(NamedParameterJdbcTemplate jdbcTemplate, DefaultObjectMapper objectMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.objectMapper = objectMapper;
	}

	@Override
	protected void doSave(Employee employee) {
		String sql = "INSERT INTO my_employee (ID,USERNAME,JSON_CONTENT) VALUES (:id,:username,:json) "
				+ "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
		//Map<String, String> paramMap = of("id", employee.getId(), "username", employee.getUserName(), "json",
				//objectMapper.writeValueAsString(employee));
		//jdbcTemplate.update(sql, paramMap);
	}

//
}
