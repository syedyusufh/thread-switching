package com.integration.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.MessageChannel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ErrorFlowConfig {

	@Bean
	public MessageChannel appErrorChannel() {
		return new DirectChannel();
	}

	// @Bean
	public IntegrationFlow appErrorChannelFlow() {

		// @formatter:off
		return IntegrationFlow.from(appErrorChannel())
							.bridge()
							.handle(m -> log.error("Error Message Consumed"))
							.get();
		// @formatter:on
	}

}
