/*
package com.airport.security.filter;

import com.airport.security.ApplicationHeaders;
import com.airport.security.util.TokenUtil;
import com.airport.service.impl.PassengerAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class AuthenticateTokenFilter extends UsernamePasswordAuthenticationFilter {

	private final TokenUtil tokenUtil;

	private final PassengerAuthService userPassengerAuthService;


	@Override
	public void doFilter (ServletRequest req,
	                      ServletResponse res,
	                      FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) req;
		String authToken = httpServletRequest.getHeader (ApplicationHeaders.AUTH_TOKEN);

		if (authToken != null) {

			String username = tokenUtil.getUserFromToken (authToken);
			if (username != null && SecurityContextHolder.getContext ()
			                                             .getAuthentication () == null) {
				UserDetails userDetails = userPassengerAuthService.loadUserByUsername (username);

				if (tokenUtil.validateToken (authToken, userDetails)) {

					UsernamePasswordAuthenticationToken authenticationToken =
							new UsernamePasswordAuthenticationToken (userDetails,
									null, userDetails.getAuthorities ());

					authenticationToken.setDetails (new WebAuthenticationDetailsSource ()
							                                .buildDetails (httpServletRequest));

					SecurityContextHolder.getContext ()
					                     .setAuthentication (authenticationToken);

					log.info ("Add token in security context");
				}
			}
		}
		chain.doFilter (req, res);
	}

}
*/
