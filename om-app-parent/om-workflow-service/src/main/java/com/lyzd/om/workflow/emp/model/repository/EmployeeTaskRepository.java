package com.lyzd.om.workflow.emp.model.repository;

import static com.google.common.collect.ImmutableMap.of;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.lyzd.om.emp.sdk.audit.representation.EmployeeApplyRepresentation;
import com.lyzd.om.shared.exception.MyException;
import com.lyzd.om.shared.jackson.DefaultObjectMapper;
import com.lyzd.om.shared.model.BaseRepository;
import com.lyzd.om.spring.common.dto.Result;
import com.lyzd.om.spring.common.dto.ResultCode;
import com.lyzd.om.workflow.emp.model.EmpInfoUpdateApplyTask;

/**
 * 员工信息更新申请
 * @author Thinker
 *
 */
@Component
public class EmployeeTaskRepository extends BaseRepository<EmpInfoUpdateApplyTask> {
	
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    private DefaultObjectMapper objectMapper;
    
    private static String SELECT_SQL = "SELECT JSON_CONTENT FROM My_EmployeeTask where userId=:userId LIMIT :limit OFFSET :offset order by createdAt desc;";
    private static String COUNT_SQL = "SELECT COUNT(1) FROM My_EmployeeTask where userId=:userId;";
    
    @Autowired
	public EmployeeTaskRepository(NamedParameterJdbcTemplate jdbcTemplate,
                             DefaultObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doSave(EmpInfoUpdateApplyTask ea) {
        String sql = "INSERT INTO My_EmployeeTask (ID, JSON_CONTENT) VALUES (:id, :json) " +
                "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
        Map<String, String> paramMap = of("id", ea.getId(), "json", objectMapper.writeValueAsString(ea));
        jdbcTemplate.update(sql, paramMap);
        
    }
    
    public EmpInfoUpdateApplyTask byId(String id) {
        try {
            String sql = "SELECT JSON_CONTENT FROM My_EmployeeTask WHERE ID=:id;";
            return jdbcTemplate.queryForObject(sql, of("id", id), mapper());
        } catch (EmptyResultDataAccessException e) {
        	throw new MyException(ResultCode.NOT_FOUND, "未找到数据: " + id);
        }
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
     // @Transactional(readOnly = true) //注意：不能使用该注解，否则父类中的eventDao无法注入
    public Result queryApply(String userId, int currentPage, int pageSize) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("userId", userId);
        parameters.addValue("limit", pageSize);
        parameters.addValue("offset", (currentPage - 1) * pageSize);
       

        List<EmployeeApplyRepresentation> res = jdbcTemplate.query(SELECT_SQL, parameters,
                (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), EmployeeApplyRepresentation.class)
        );

        long total = jdbcTemplate.queryForObject(COUNT_SQL,  of("userId", userId), Integer.class);
        return Result.ok().count(total).data(res);
    }


//    @Transactional(readOnly = true)
//    public PagedResource<HrAudit> listApply(int pageIndex, int pageSize) {
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("limit", pageSize);
//        parameters.addValue("offset", (pageIndex - 1) * pageSize);
//
//        List<HrAudit> res = jdbcTemplate.query(SELECT_SQL, parameters,
//                (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"),
//                		HrAudit.class)
//        );
//
//        int total = jdbcTemplate.queryForObject(COUNT_SQL, newHashMap(), Integer.class);
//        return PagedResource.of(total, pageIndex, res);
//    }


    private RowMapper<EmpInfoUpdateApplyTask> mapper() {
        return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), EmpInfoUpdateApplyTask.class);
    }
    
    /**
     * Conditional query.
     * @param name
     * @param deptName
     * @param startTime
     * @param endTime
     * @param currentPage
     * @param pageSize
     * @return
     */
    public Result queryTasks(String name, String deptName, String startTime, String endTime,  int currentPage, int pageSize) {
    	StringBuilder sql = new StringBuilder("SELECT JSON_CONTENT FROM My_EmployeeTask where isPass <> '2' ");
    	StringBuilder countSql = new StringBuilder("SELECT COUNT(1) FROM My_EmployeeTask where isPass <> '2' ");
    	
    	MapSqlParameterSource parameters = new MapSqlParameterSource();
    	//parameters.addValue("isPass", "1");
    	//sql.append(" and isPass=:isPass ");
        
    	if(name != null && name.trim().length() > 0) {
    		sql.append(" and name=:name ");
    		parameters.addValue("name", name);
    		countSql.append("and name=:name ");
    	}
    	if(deptName != null && deptName.trim().length() > 0) {
    		sql.append(" and deptName=:deptName ");
    		parameters.addValue("deptName", deptName);
    		countSql.append(" and deptName=:deptName ");
    	}
    	if(startTime != null && deptName.trim().length() > 0) {
    		sql.append(" and createdAt >=:startTime ");
    		parameters.addValue("startTime", startTime);
    		countSql.append(" and createdAt >=:startTime ");
    	}
    	if(endTime != null && deptName.trim().length() > 0) {
    		sql.append(" and createdAt <:endTime ");
    		parameters.addValue("endTime", endTime);
    		countSql.append(" and createdAt <:endTime ");
    	}
    	
    	if(currentPage > 0 && pageSize > 0) {
    		sql.append(" LIMIT :limit OFFSET :offset ");
    		parameters.addValue("limit", pageSize);
            parameters.addValue("offset", (currentPage - 1) * pageSize);
    	}
    	
    	sql.append(";");
    	
    	List<EmployeeApplyRepresentation> res = jdbcTemplate.query(sql.toString(), parameters,
                (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), EmployeeApplyRepresentation.class)
        );

        long total = jdbcTemplate.queryForObject(countSql.toString(),  parameters, Integer.class);
        return Result.ok().count(total).data(res);
    	
    }

}
