package com.lyzd.om.emp.info.service;

import static com.google.common.collect.Maps.newHashMap;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.lyzd.om.emp.info.representation.EmployeeRepresentation;
import com.lyzd.om.shared.utils.PagedResource;

public class EmployeeSearchService {
    private static final String SELECT_SQL = "SELECT ID, NAME FROM USERS LIMIT :limit OFFSET :offset;";
    private static final String COUNT_SQL = "SELECT COUNT(1) FROM USERS;";


//    private final NamedParameterJdbcTemplate jdbcTemplate;
////    private final UserRepository repository;
//	 @Transactional(readOnly = true)
//	    public PagedResource<EmployeeRepresentation> listEmployee(int pageIndex, int pageSize) {
//	        MapSqlParameterSource parameters = new MapSqlParameterSource();
//	        parameters.addValue("limit", pageSize);
//	        parameters.addValue("offset", (pageIndex - 1) * pageSize);
//
//	        List<EmployeeRepresentation> Users = jdbcTemplate.query(SELECT_SQL, parameters,
//	                (rs, rowNum) -> new EmployeeRepresentation(rs.getString("ID"),
//	                        rs.getString("NAME")));
//
//	        int total = jdbcTemplate.queryForObject(COUNT_SQL, newHashMap(), Integer.class);
//	        return PagedResource.of(total, pageIndex, Users);
//	    }

}
