package com.lyzd.om.spring.common.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lyzd.om.shared.event.DomainEventConsumeRecorder;
import com.lyzd.om.shared.event.DomainEventDao;
import com.lyzd.om.shared.event.DomainEventPublisher;
import com.lyzd.om.shared.event.DomainEventSender;
import com.lyzd.om.shared.utils.DistributedLockExecutor;
import com.lyzd.om.spring.common.event.DefaultDomainEventPublisher;
import com.lyzd.om.spring.common.event.DomainEventBackupPublishScheduler;

/**
 * @author Thinker
 *
 */
@Configuration
public class DomainEventCommonConfiguration {

    @Bean
    public DomainEventPublisher domainEventPublisher(DomainEventDao eventDao,
                                                     DistributedLockExecutor lockExecutor,
                                                     DomainEventSender eventSender) {
        return new DefaultDomainEventPublisher(eventDao,
                lockExecutor,
                eventSender);
    }

    @Bean
    public DomainEventBackupPublishScheduler domainEventBackupPublishScheduler(DomainEventPublisher eventPublisher) {
        return new DomainEventBackupPublishScheduler(eventPublisher);
    }

    @Bean
    public DomainEventConsumeWrapper domainEventConsumingWrapper(DomainEventConsumeRecorder eventRecorder) {
        return new DomainEventConsumeWrapper(eventRecorder);
    }

}
