package com.edivanio.curso.boot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edivanio.curso.boot.model.User;
import com.edivanio.curso.boot.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository repo;
	
	@GetMapping("/user")
	public List<User> getAllUsers(){
		return repo.findAll();
	}
	
	@GetMapping("/user/{id}")
	public  ResponseEntity<User>  getUserById(@PathVariable Long id ){
		return repo.findById(id).map(u -> ResponseEntity.ok().body(u) )
				.orElse(ResponseEntity.notFound().build());
				
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
		return  new ResponseEntity<>(repo.save(user),HttpStatus.CREATED);
	}
	

	@PutMapping("/user/{id}") 
	public ResponseEntity<User>  updateUser(@PathVariable("id") Long id , @RequestBody User user) {
		return repo.findById(id)
				.map(save -> {
					save.setName(user.getName());
					save.setPassword(user.getPassword());
					save.setEmail(user.getEmail());
					User updated = repo.save(save);
					return ResponseEntity.ok().body(updated);
				}).orElse(ResponseEntity.notFound().build());
	}
	
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable Long id){
		 repo.deleteById(id);;
	}
}
