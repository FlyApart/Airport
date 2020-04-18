package com.airport.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
@EqualsAndHashCode
@Data
public class JwtPassenger implements UserDetails {

	private final String password;
	private final String username;
	private final Collection<? extends GrantedAuthority> authorities;
	private final Long id;
	private final String name;
	private final String secondName;
	private final boolean enabled;

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired () {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked () {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired () {
		return true;
	}
}
