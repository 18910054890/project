package com.lyzd.om.spring.common.event.messaging.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.lyzd.om.spring.common.event.DomainEventConsumeWrapper;

/**
 * @author Thinker
 *
 */
@Slf4j
@Aspect
public class RabbitDomainEventConsumeAspect {
    private DomainEventConsumeWrapper consumeWrapper;

    public RabbitDomainEventConsumeAspect(DomainEventConsumeWrapper consumeWrapper) {
        this.consumeWrapper = consumeWrapper;
    }

    @Around("@annotation(org.springframework.amqp.rabbit.annotation.RabbitHandler) ||" +
            " @annotation(org.springframework.amqp.rabbit.annotation.RabbitListener)")
    public Object recordEvents(ProceedingJoinPoint joinPoint) throws Throwable {
        return consumeWrapper.recordAndConsume(joinPoint);
    }
}