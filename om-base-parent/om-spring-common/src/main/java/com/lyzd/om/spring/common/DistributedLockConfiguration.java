package com.lyzd.om.spring.common;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lyzd.om.shared.utils.DistributedLockExecutor;

import javax.sql.DataSource;

/**
 * 
 * @author Thinker
 *
 */
@Configuration
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class DistributedLockConfiguration {

    @Bean
    public LockProvider lockProvider(DataSource dataSource) {
        return new JdbcTemplateLockProvider(dataSource);
    }

    @Bean
    public DistributedLockExecutor distributedLockExecutor(LockProvider lockProvider) {
        return new DistributedLockExecutor(lockProvider);
    }


}
