package com.amd.apidio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.amd.apidio.resources"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(buildApiInfo());
	}

	//A classe ApiInfo é utilizada para passar informações para a documentação da API
	//URL: http://localhost:8080/swagger-ui.html#/
	private ApiInfo buildApiInfo() {
		
		return new ApiInfoBuilder()
				.title("API Clientes")
				.description("REST API Clientes para consulta, inclusão, alteração, exclusão e consulta paginada com bancos H2 e MySQL")
				.version("1.0.0")
				.contact(new Contact(
						"Andrey Martins",
						"github/andreymartins",
						"andreydeus@gmail.com"))
				.build();
		
	}
	
	
}
