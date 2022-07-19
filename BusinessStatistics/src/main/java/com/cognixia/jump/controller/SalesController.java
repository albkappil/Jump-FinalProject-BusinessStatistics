package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Department;
import com.cognixia.jump.model.Sales;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.SalesRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api")
@Tag(name = "sales", description = "API for user actions")
public class SalesController {
	
	@Autowired
	SalesRepository repo;
	
	@GetMapping("/sales")
	public List<Sales> getSales() {
		return repo.findAll();
	}
	
	@GetMapping("/sales/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id) {
		Optional<Sales> found = repo.findById(null);
		
		if (found.isEmpty()) {
			return ResponseEntity.status(404).body("Sale with id: " + id + " was not found.");
		}
		else {
			return ResponseEntity.status(200).body(found.get());
		}
	}
	
	@PostMapping("/sales")
	public ResponseEntity<?> createDepartment(@RequestBody Sales sale) {
		
		sale.setId(null);
		
		Sales created = repo.save(sale);
		
		return ResponseEntity.status(201).body(created);
	}
	
	@PutMapping("/sales")
	public ResponseEntity<?> updateUser(@RequestBody Sales sale) {
		boolean exists = repo.existsById(sale.getId());
		
		if (!exists) {
			return ResponseEntity.status(404).body("User not updated - user not found");
		}
		else {
			Sales updated = repo.save(sale);
			return ResponseEntity.status(200).body(updated);
		}
	}
	
	@DeleteMapping("/sales")
	public ResponseEntity<?> deleteUser(@RequestBody Sales sale) {
		Optional<Sales> found = repo.findById(sale.getId());
		
		if (found.isEmpty()) {
			return ResponseEntity.status(404).body("User not deleted - user with id: " + sale.getId() + " was not found");
		}
		else {
			repo.deleteById(found.get().getId());
			return ResponseEntity.status(200).body("User with id: " + found.get().getId() + " deleted");
		}
	}
	
	@DeleteMapping("/sales/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable int id) {
		Optional<Sales> found = repo.findById(id);
		
		if (found.isEmpty()) {
			return ResponseEntity.status(404).body("User not deleted - user with id: " + id + " was not found");
		}
		else {
			repo.deleteById(found.get().getId());
			return ResponseEntity.status(200).body("User with id: " + found.get().getId() + " deleted");
		}
	}
	
}
