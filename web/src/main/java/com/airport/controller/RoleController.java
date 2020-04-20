package com.airport.controller;


import com.airport.entity.Role;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.RoleRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin
@RestController
@RequestMapping(value = "/admin/role")
@RequiredArgsConstructor
public class RoleController {

	private final RoleRepository roleRepository;

	@ApiOperation(value = "Find all roleы")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 500, message = "Error processing request")
	})

			@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@GetMapping
	public ResponseEntity<Page<Role>> findAllRole (@ApiIgnore Pageable pageable) {
		return new ResponseEntity<> (roleRepository.findAll (pageable), HttpStatus.OK);
	}

	@ApiOperation(value = "Find role by id")
	@ApiResponses({
			@ApiResponse(code = 201, message = "Request has succeeded"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 403, message = "Access denied"),
			@ApiResponse(code = 404, message = "Not found a current representation for the target"),
			@ApiResponse(code = 500, message = "Error processing request")
	})
	@ApiImplicitParam(name = "Auth-Token", value = "Auth-Token", required = true, dataType = "string", paramType = "header")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Role> findRoleById (@PathVariable("id") String id) {
		Role role = roleRepository.findById (Long.valueOf (id))
		                          .orElseThrow (() -> new EntityNotFoundException (Role.class, id));
		return new ResponseEntity<> (role, HttpStatus.OK);
	}

}
