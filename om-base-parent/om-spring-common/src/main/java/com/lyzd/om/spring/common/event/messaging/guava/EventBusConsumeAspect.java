package com.lyzd.om.spring.common.event.messaging.guava;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.lyzd.om.spring.common.event.DomainEventConsumeWrapper;

/**
 * @author Thinker
 *
 */
@Slf4j
@Aspect
public class EventBusConsumeAspect {
	
    private DomainEventConsumeWrapper consumeWrapper;

    public EventBusConsumeAspect(DomainEventConsumeWrapper consumeWrapper) {
        this.consumeWrapper = consumeWrapper;
    }
    
    @Pointcut("@annotation(com.lyzd.om.shared.annotation.ConsumerPointCut)")
    public void annotationPointcut() {
    }

    @Around("annotationPointcut()")
    public Object recordEvents(ProceedingJoinPoint joinPoint) throws Throwable {
        return consumeWrapper.recordAndConsume(joinPoint);
    }
}