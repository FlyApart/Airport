package com.airport.security.util;

import com.airport.entity.RoleName;
import com.airport.security.model.JwtPassenger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;
import java.util.Collection;
import java.util.stream.Collectors;

public class PrincipalUtil {

	public static String getLogin (Principal principal) {
		Object castedPrincipal = ((UsernamePasswordAuthenticationToken) principal).getPrincipal ();
		return ((JwtPassenger) castedPrincipal).getUsername ();
	}

	public static String getName (Principal principal) {
		Object castedPrincipal = ((UsernamePasswordAuthenticationToken) principal).getPrincipal ();
		return ((JwtPassenger) castedPrincipal).getName ();
	}

	public static String getSecondName (Principal principal) {
		Object castedPrincipal = ((UsernamePasswordAuthenticationToken) principal).getPrincipal ();
		return ((JwtPassenger) castedPrincipal).getSecondName ();
	}

	public static Long getId (Principal principal) {
		Object castedPrincipal = ((UsernamePasswordAuthenticationToken) principal).getPrincipal ();
		return ((JwtPassenger) castedPrincipal).getId ();
	}

	public static String getPassword (Principal principal) {
		Object castedPrincipal = ((UsernamePasswordAuthenticationToken) principal).getPrincipal ();
		return ((JwtPassenger) castedPrincipal).getPassword ();
	}

	public static Collection<GrantedAuthority> getAuthority (Principal principal) {
		Object castedPrincipal = ((UsernamePasswordAuthenticationToken) principal).getPrincipal ();
		Collection<GrantedAuthority> collection;
		try {
			collection = (Collection<GrantedAuthority>) ((JwtPassenger) castedPrincipal).getAuthorities ();
		} catch (ClassCastException e) {
			throw new ClassCastException (e.getMessage ());
		}
		return collection;
	}

	public static Collection<RoleName> getRoles (Principal principal) {

		Collection<GrantedAuthority> collection = getAuthority (principal);

		return collection.stream ()
		                 .map (GrantedAuthority::getAuthority)
		                 .map (s -> s.replace ("ROLE_", ""))
		                 .map (RoleName::valueOf)
		                 .collect (Collectors.toList ());
	}

	public static Long checkRoles (Principal principal) {
		Collection<RoleName> authority = getRoles (principal);
		return  authority.stream ()
		                      .filter (p -> (p.equals (RoleName.ADMIN) || p.equals (RoleName.MODER)))
		                      .count ();
	}



	public static boolean getAccess (Principal principal, Long id) {
		return checkRoles (principal) != 0 || id.equals (getId (principal));
	}

	public static boolean getAccess (Principal principal, String login) {
		return checkRoles (principal) != 0 || login.equals (getLogin (principal));
	}
}
