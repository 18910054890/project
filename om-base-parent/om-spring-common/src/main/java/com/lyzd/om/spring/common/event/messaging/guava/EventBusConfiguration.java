package com.lyzd.om.spring.common.event.messaging.guava;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.google.common.eventbus.EventBus;
import com.lyzd.om.shared.event.DomainEventPublisher;
import com.lyzd.om.shared.event.DomainEventSender;
import com.lyzd.om.spring.common.event.DomainEventConsumeWrapper;

/**
 * @author Thinker
 *
 */
@Configuration
class EventBusConfiguration {

	@Bean
	@Primary
	public DomainEventSender domainEventSender(EventBus eventBus) {
		return new EventBusSender(eventBus);
	}

	@Bean
	public EventBusConsumeAspect eventBusConsumeAspect(DomainEventConsumeWrapper wrapper) {
		return new EventBusConsumeAspect(wrapper);
	}

	@Bean
	public EventBusPublishAspect EventBusPublishAspect(DomainEventPublisher publisher) {
		return new EventBusPublishAspect(publisher);
	}

	@Bean
	public EventBus configEvent() {
		EventBus eventBus = new EventBus();
		return eventBus;
	}

}
