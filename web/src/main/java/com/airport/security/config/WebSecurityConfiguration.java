package com.airport.security.config;

import com.airport.security.filter.JwtAuthenticationFilter;
import com.airport.security.filter.JwtAuthorizationFilter;
import com.airport.security.util.PassengerAuthService;
import com.airport.security.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final PassengerAuthService passengerAuthService;
	private final TokenUtil tokenUtil;
	private final SecurityConstants securityConstants;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean () throws Exception {
		return super.authenticationManagerBean ();
	}

	@Override
	protected void configure (HttpSecurity httpSecurity) throws Exception {

		httpSecurity
					.httpBasic ().disable ()
					.csrf ().disable ()
		            .exceptionHandling ()

		            .and ()
		            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

					.and()
		            .authorizeRequests ()
		            .antMatchers ("/v2/api-docs", "/configuration/ui/**", "/swagger-resources/**",
				            "/configuration/security/**", "/swagger-ui.html", "/webjars/**").permitAll ()
		            .antMatchers ("/actuator/**").permitAll ()
		            .antMatchers (HttpMethod.GET, "/swagger-ui.html#").permitAll ()
		            .antMatchers (HttpMethod.OPTIONS, "/**").permitAll ()
		            .antMatchers ("/login/**").permitAll ()
		            .antMatchers ("/registration/**").permitAll ()

					.antMatchers (HttpMethod.GET,"/rest/**").hasAnyRole ("USER","ADMIN", "MODER")

					.antMatchers ("/rest/tickets/**","/rest/passenger/**").hasAnyRole ("USER","ADMIN", "MODER")

					.antMatchers ("/rest/**").hasAnyRole ("ADMIN", "MODER")
		            .antMatchers ("/admin/**").hasRole ("ADMIN")
		            .anyRequest ().authenticated ()
					.and ()
					.addFilter(new JwtAuthenticationFilter (
							authenticationManager(),
							passengerAuthService,
							tokenUtil,securityConstants))
					.addFilter(new JwtAuthorizationFilter (
							authenticationManager(),
							passengerAuthService,
							tokenUtil,securityConstants));

	}

	@Override
	public void configure (WebSecurity web) {
		web.ignoring ()
		   .antMatchers ("/v2/api-docs", "/configuration/ui/**", "/swagger-resources/**", "/configuration/security/**", "/swagger-ui.html", "/webjars/**");
	}
}
