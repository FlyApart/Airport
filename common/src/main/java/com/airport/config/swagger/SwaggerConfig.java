package com.airport.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Time;
import java.time.LocalTime;

@Configuration
@EnableSwagger2
@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

	@Bean
	public Docket api () {

		return new Docket (DocumentationType.SWAGGER_2)
				       .directModelSubstitute(LocalTime.class, Time.class)
				       .select ()
				       .apis (RequestHandlerSelectors.any ())
				       .paths (PathSelectors.any ())

				       .build ()
				       .apiInfo (apiInfo ());
	}

	private ApiInfo apiInfo () {
		return new ApiInfoBuilder ().title ("REST API")
		                            .description ("HTP Test REST API")
		                            .contact (new Contact ("Zuev Alexandr", "", "oglorn@gmail.com"))
		                            .license ("Apache 2.0")
		                            .licenseUrl ("http://www.apache.org/licenses/LICENSE-2.0.html")
		                            .version ("1.0.0")
		                            .build ();
	}

}