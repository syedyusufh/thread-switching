package com.integration.sample.handler;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class MockDBHandler {

	public Mono<Message<String>> fetchName() {

		// @formatter:off
		return Mono.fromCallable(() -> "David")
					.doOnSuccess(newMessage -> log.info("DB call is successful"))
					.map(name -> MessageBuilder.withPayload(name)
												.setHeader("HDR_GETTING_LOST", "someValue")
												.build());
		// @formatter:on
	}

}
