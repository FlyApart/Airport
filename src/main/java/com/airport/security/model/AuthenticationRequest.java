package com.airport.security.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

@ApiModel(description = "Values for authentication")
public class AuthenticationRequest {

	@NotEmpty
	@Email
	@ApiModelProperty(required = true, dataType = "String", notes = "login")
	String login;

	@NotEmpty
	@ApiModelProperty(required = true, dataType = "String", notes = "password")
	String password;

}
