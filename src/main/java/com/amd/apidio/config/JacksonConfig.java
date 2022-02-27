package com.amd.apidio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

/* Esse código é padrão da biblioteca do Jackson, o que muda são as subclasses que serão registradas */

@Configuration
public class JacksonConfig {
	// tópico onde é discutida a criação dessa configuração 
	// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}
