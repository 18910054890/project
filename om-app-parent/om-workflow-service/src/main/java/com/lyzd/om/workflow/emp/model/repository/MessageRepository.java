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
import org.springframework.transaction.annotation.Transactional;

import com.lyzd.om.shared.exception.MyException;
import com.lyzd.om.shared.jackson.DefaultObjectMapper;
import com.lyzd.om.shared.model.BaseRepository;
import com.lyzd.om.shared.utils.PagedResource;
import com.lyzd.om.spring.common.dto.Result;
import com.lyzd.om.spring.common.dto.ResultCode;
import com.lyzd.om.workflow.emp.model.Message;

import static com.google.common.collect.Maps.newHashMap;

/**
 * Employee's message
 * @author Thinker
 *
 */
@Component
public class MessageRepository extends BaseRepository<Message> {
	
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    private DefaultObjectMapper objectMapper;
    
    private static final String COUNT_SQL = "SELECT COUNT(1) FROM My_Message where isRead='0' and reader=:reader;";
    
    private static final String SELECT_SQL = "SELECT JSON_CONTENT FROM My_Message where isRead='0' and reader=:reader;";
    
    @Autowired
	public MessageRepository(NamedParameterJdbcTemplate jdbcTemplate,
                             DefaultObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doSave(Message hm) {
        String sql = "INSERT INTO My_Message (ID, JSON_CONTENT) VALUES (:id, :json) " +
                "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
        Map<String, String> paramMap = of("id", hm.getId(), "json", objectMapper.writeValueAsString(hm));
        jdbcTemplate.update(sql, paramMap);
    }
    
    public Message byId(String id) {
        try {
            String sql = "SELECT JSON_CONTENT FROM My_Message WHERE ID=:id;";
            return jdbcTemplate.queryForObject(sql, of("id", id), mapper());
        } catch (EmptyResultDataAccessException e) {
        	throw new MyException(ResultCode.NOT_FOUND, "未找到数据: " + id);
        }
    }
    
    public long count(String reader) {
    	 MapSqlParameterSource parameters = new MapSqlParameterSource();
         parameters.addValue("reader", reader);
         
    	return jdbcTemplate.queryForObject(COUNT_SQL, parameters, Integer.class);
    }
    
  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Transactional(readOnly = true)
  public Result queryMessages(String reader, int currentPage, int pageSize) {
      MapSqlParameterSource parameters = new MapSqlParameterSource();
      parameters.addValue("reader", reader);
      parameters.addValue("limit", pageSize);
      parameters.addValue("offset", (currentPage - 1) * pageSize);

      List<Message> res = jdbcTemplate.query(SELECT_SQL, parameters,
              (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), Message.class)
      );

      long total = jdbcTemplate.queryForObject(COUNT_SQL, parameters, Integer.class);
      return Result.ok().count(total).data(res);
  }

    private RowMapper<Message> mapper() {
        return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), Message.class);
    }
    
//    @Transactional(readOnly = true)
//    public PagedResource<HrMessage> queryMessages(int pageIndex, int pageSize) {
//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//        parameters.addValue("limit", pageSize);
//        parameters.addValue("offset", (pageIndex - 1) * pageSize);
//
//        List<HrMessage> res = jdbcTemplate.query(SELECT_SQL, parameters,
//                (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), HrMessage.class)
//        );
//
//        int total = jdbcTemplate.queryForObject(COUNT_SQL, newHashMap(), Integer.class);
//        return PagedResource.of(total, pageIndex, res);
//    }

}
