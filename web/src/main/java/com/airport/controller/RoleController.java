package com.airport.controller;


import com.airport.entity.Passenger;
import com.airport.entity.Role;
import com.airport.exceptions.EntityNotFoundException;
import com.airport.repository.springdata.RoleRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
					value = "Sorting criteria in the format: property(, " + "\"asc or desc\"). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")})
	@GetMapping
	public ResponseEntity<Page<Role>> findAllRole (@ApiIgnore Pageable pageable) {
		return new ResponseEntity<> (roleRepository.findAll (pageable), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Role> findRoleById (@PathVariable("id") String id) {
		Role role = roleRepository.findById (Long.valueOf (id))
		                          .orElseThrow (() -> new EntityNotFoundException (Role.class, id));
		return new ResponseEntity<> (role, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> deletePassenger (@PathVariable String id) {
		Role role = (roleRepository.findById (Long.valueOf (id))).orElseThrow (() -> new EntityNotFoundException (Passenger.class, id));
		roleRepository.delete (role);
		return new ResponseEntity<> (id, HttpStatus.OK);
	}
}
