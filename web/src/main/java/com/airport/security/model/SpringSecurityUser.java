/*
package com.airport.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SpringSecurityUser implements UserDetails {

	private Long id;
	private  String username;
	private String password;
	private String email;
	private Date lastPasswordReset;
	private Collection<? extends  GrantedAuthority> authorities;
	private Boolean AccountNonExpired = true;
	private Boolean AccountNonLocked = true;
	private Boolean CredentialsNonExpired = true;
	private Boolean enabled  = true;


	@Override
	public boolean isAccountNonExpired () {
		return false;
	}

	@Override
	public boolean isAccountNonLocked () {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired () {
		return false;
	}

	@Override
	public boolean isEnabled () {
		return false;
	}
}
*/
