package com.lyzd.om.user.service;

import static com.google.common.collect.Maps.newHashMap;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lyzd.om.shared.utils.PagedResource;
import com.lyzd.om.user.repository.UserRepository;
import com.lyzd.om.user.sdk.representation.UserRepresentation;

@Component
public class UserRepresentationService {
    private static final String SELECT_SQL = "SELECT ID, NAME FROM USERS LIMIT :limit OFFSET :offset;";
    private static final String COUNT_SQL = "SELECT COUNT(1) FROM USERS;";


    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRepository repository;

    public UserRepresentationService(NamedParameterJdbcTemplate jdbcTemplate,
                                        UserRepository repository) {
        this.jdbcTemplate = jdbcTemplate;
        this.repository = repository;
    }


    @Transactional(readOnly = true)
    public UserRepresentation byId(String id) {
        return repository.byId(id).toRepresentation();
    }

    @Transactional(readOnly = true)
    public PagedResource<UserRepresentation> listUsers(int pageIndex, int pageSize) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("limit", pageSize);
        parameters.addValue("offset", (pageIndex - 1) * pageSize);

        List<UserRepresentation> Users = jdbcTemplate.query(SELECT_SQL, parameters,
                (rs, rowNum) -> new UserRepresentation(rs.getString("ID"),
                        rs.getString("NAME")));

        int total = jdbcTemplate.queryForObject(COUNT_SQL, newHashMap(), Integer.class);
        return PagedResource.of(total, pageIndex, Users);
    }



}
