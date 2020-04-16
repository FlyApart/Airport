package com.airport.security.config;

import com.airport.security.filter.AuthenticateTokenFilter;
import com.airport.security.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;

	private final TokenUtil tokenUtil;
	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public void configureAuthentication (AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService (userDetailsService)
		                            .passwordEncoder (passwordEncoder);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean () throws Exception {
		return super.authenticationManagerBean ();
	}

	@Bean
	public AuthenticateTokenFilter authenticateTokenFilterBean (AuthenticationManager authenticationManager) {
		AuthenticateTokenFilter authenticateTokenFilter = new AuthenticateTokenFilter (tokenUtil, userDetailsService);
		authenticateTokenFilter.setAuthenticationManager (authenticationManager);
		return authenticateTokenFilter;
	}

	@Override
	protected void configure (HttpSecurity httpSecurity) throws Exception {
		httpSecurity
					//.httpBasic ().disable ()
					.csrf ().disable ()
		            .exceptionHandling ()
		            .and ()
		            .sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and()
		            .authorizeRequests ()
		            .antMatchers ("/v2/api-docs", "/configuration/ui/**", "/swagger-resources/**",
				            "/configuration/security/**", "/swagger-ui.html", "/webjars/**").permitAll ()
		            .antMatchers ("/actuator/**").permitAll ()
		            .antMatchers (HttpMethod.GET, "/swagger-ui.html#").permitAll ()
		            .antMatchers (HttpMethod.OPTIONS, "/**").permitAll ()

		            .antMatchers ("/quest/**").permitAll ()
		            .antMatchers ("/authentication/**").permitAll ()
		            .antMatchers ("/registration/**").permitAll ()
		            .antMatchers ("/rest/**").hasAnyRole ("USER","ADMIN", "user","ROLE_USER")
		            .antMatchers ("/admin/**").hasAnyRole ("ADMIN")
		            .anyRequest ().authenticated ();

		httpSecurity.addFilterBefore (authenticateTokenFilterBean (authenticationManagerBean ()), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure (WebSecurity web) {
		web.ignoring ()
		   .antMatchers ("/v2/api-docs", "/configuration/ui/**", "/swagger-resources/**", "/configuration/security/**", "/swagger-ui.html", "/webjars/**");
	}
}
