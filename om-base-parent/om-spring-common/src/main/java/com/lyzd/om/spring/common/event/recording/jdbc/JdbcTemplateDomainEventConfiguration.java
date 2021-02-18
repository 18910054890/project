package com.lyzd.om.spring.common.event.recording.jdbc;

import com.lyzd.om.shared.event.DomainEventConsumeRecorder;
import com.lyzd.om.shared.event.DomainEventDao;
import com.lyzd.om.shared.jackson.DefaultObjectMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author Thinker
 *
 */
@Configuration
public class JdbcTemplateDomainEventConfiguration {
	
	

    public JdbcTemplateDomainEventConfiguration() {
		super();
	}

	@Bean
    public DomainEventDao domainEventDao(NamedParameterJdbcTemplate jdbcTemplate,
                                         DefaultObjectMapper objectMapper) {
        return new JdbcTemplateDomainEventDao(jdbcTemplate, objectMapper);
    }

    @Bean
    public DomainEventConsumeRecorder domainEventConsumeRecorder(NamedParameterJdbcTemplate jdbcTemplate) {
        return new JdbcTemplateDomainEventConsumeRecorder(jdbcTemplate);
    }


    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
