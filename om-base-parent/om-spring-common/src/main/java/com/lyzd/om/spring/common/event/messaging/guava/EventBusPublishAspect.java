package com.lyzd.om.spring.common.event.messaging.guava;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.lyzd.om.shared.event.DomainEventPublisher;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Thinker
 *
 */
@Slf4j
@Aspect
public class EventBusPublishAspect {
    private TaskExecutor taskExecutor;
    private DomainEventPublisher publisher;

    public EventBusPublishAspect(DomainEventPublisher publisher) {
        this.taskExecutor = taskExecutor();
        this.publisher = publisher;
    }

    private TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setQueueCapacity(500);
        executor.setRejectedExecutionHandler((r, e)
                -> log.debug("Domain event publish job rejected silently."));
        executor.setThreadNamePrefix("domain-event-publish-executor-");
        executor.initialize();
        return executor;
    }
    
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
             "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
             "@annotation(org.springframework.web.bind.annotation.PatchMapping) || " +
             "@annotation(org.springframework.web.bind.annotation.DeleteMapping) ||" +
             "@annotation(com.google.common.eventbus.Subscribe)")
    public void annotationPointcut() {
    }

    @After("annotationPointcut()")
    public void publishEvents(JoinPoint joinPoint) {
        log.info("Trigger domain event publish process using Spring AOP.");
        taskExecutor.execute(() -> publisher.publishNextBatch());
    }
}