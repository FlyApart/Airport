package com.airport.config.swagger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

@Configuration
@EnableSwagger2
@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

	private static final Logger LOG = LogManager.getLogger (SwaggerConfig.class);

	@Bean
	public Docket api () {

		LOG.debug ("Starting swagger");

		return new Docket (DocumentationType.SWAGGER_2)
				       // .host("http://localhost:8080")
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