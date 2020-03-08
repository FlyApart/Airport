package com.airline.controller;

		import com.airline.entity.Passengers;
		import com.airline.service.impl.HibernatePassengersServiceIml;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.http.HttpStatus;
		import org.springframework.http.ResponseEntity;
		import org.springframework.stereotype.Controller;
		import org.springframework.web.bind.annotation.GetMapping;
		import org.springframework.web.bind.annotation.RequestMapping;

		import java.util.List;

@Controller
@RequestMapping("/rest/hibernateExample")
public class HPC {
	@Autowired
	private HibernatePassengersServiceIml hibernatePassengersServiceIml;

	@GetMapping
	public ResponseEntity<List<Passengers>> getPassengers() {
		return new ResponseEntity<>(hibernatePassengersServiceIml.findAll(), HttpStatus.OK);
	}
}
