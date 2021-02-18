package com.lyzd.om.user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.lyzd.om.shared.jackson.DefaultObjectMapper;
import com.lyzd.om.shared.model.BaseRepository;
import com.lyzd.om.user.exception.UserNotFoundException;
import com.lyzd.om.user.model.User;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;

/**
 * @author Thinker
 *
 */
@Component
public class UserRepository extends BaseRepository<User> {
	
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    private DefaultObjectMapper objectMapper;
    
    
    @Autowired
	public UserRepository(NamedParameterJdbcTemplate jdbcTemplate,
                             DefaultObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doSave(User user) {
        String sql = "INSERT INTO USERS (ID, NAME,JSON_CONTENT) VALUES (:id, :name, :json) " +
                "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
        Map<String, String> paramMap = of("id", user.getId(), "name", user.getName(), "json", objectMapper.writeValueAsString(user));
        jdbcTemplate.update(sql, paramMap);
    }

    public User byId(String id) {
        try {
            String sql = "SELECT JSON_CONTENT FROM USERS WHERE ID=:id;";
            return jdbcTemplate.queryForObject(sql, of("id", id), mapper());
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(id);
        }
    }


    private RowMapper<User> mapper() {
        return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), User.class);
    }

}
