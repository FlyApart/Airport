package com.airport.security.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;
import java.util.Collection;

public class PrincipalUtil {

	public static String getLogin (Principal principal) {
		Object castedPrincipal = ((UsernamePasswordAuthenticationToken) principal).getPrincipal ();
		return ((User) castedPrincipal).getUsername ();
	}

	public static String getPassword (Principal principal) {
		Object castedPrincipal = ((UsernamePasswordAuthenticationToken) principal).getPrincipal ();
		return ((User) castedPrincipal).getPassword ();
	}

	public static Collection<GrantedAuthority> getAuthority (Principal principal) {
		Object castedPrincipal = ((UsernamePasswordAuthenticationToken) principal).getPrincipal ();
		return ((User) castedPrincipal).getAuthorities ();
	}
}
