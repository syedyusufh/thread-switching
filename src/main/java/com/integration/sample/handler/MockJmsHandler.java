package com.integration.sample.handler;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class MockJmsHandler {

	public Mono<Void> publishName(final String fetchedName) {

		// @formatter:off
		return Mono.fromCallable(() -> "Published " + fetchedName + " to Jms")
					.doOnSuccess(newMessage -> log.info("JMS call is successful"))
					.then();
		// @formatter:on
	}

}
