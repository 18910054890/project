package com.lyzd.om.spring.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lyzd.om.shared.jackson.DefaultObjectMapper;

/**
 * @author Thinker
 *
 */
@Configuration
public class ObjectMapperConfiguration {

    @Bean
    public DefaultObjectMapper objectMapper() {
        return new DefaultObjectMapper();
    }

}
