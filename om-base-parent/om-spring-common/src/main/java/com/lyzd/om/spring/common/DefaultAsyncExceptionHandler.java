package com.lyzd.om.spring.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * @author Thinker
 *
 */
@Slf4j
public class DefaultAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.error("Error while executing async task-{}:", method.getName(), ex);
    }
}
