package com.integration.sample.config;

import static org.springframework.integration.dsl.Pollers.fixedDelay;
import static org.springframework.integration.handler.LoggingHandler.Level.INFO;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.MessageChannel;

import com.integration.sample.handler.MockDBHandler;
import com.integration.sample.handler.MockJmsHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
public class SimpleFlowConfig {

	private MockDBHandler mockDBHandler;

	private MockJmsHandler mockJmsHandler;

	@Bean
	public IntegrationFlow restoreReactorContextFlow(MessageChannel appErrorChannel) {

		// @formatter:off
		return IntegrationFlow.fromSupplier(() -> "Good Morning", ec -> ec.poller(fixedDelay(Duration.ofSeconds(30))
																		.errorChannel(appErrorChannel)))
							.log(INFO, m -> "************ Flow started *****************")
							.enrichHeaders(hdrSpec -> hdrSpec.errorChannel(appErrorChannel))
							
							.handle(mockDBHandler, "fetchName", ec -> ec.async(true))
							.handle(mockJmsHandler, "publishName", ec -> ec.async(true))
							.get();
		// @formatter:on
	}

}
