package com.asb.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LdapController {

	@Autowired
	private PersonRepo personRepo;
	
	@GetMapping("/get-user-names")
	public ResponseEntity<List<String>> getLdapUserNames() {
		return new ResponseEntity<>(personRepo.getAllPersonNames(), HttpStatus.OK);
	}

	@GetMapping("/get-users")
	public ResponseEntity<List<Person>> getLdapUsers() {
		return new ResponseEntity<>(personRepo.getAllPersons(), HttpStatus.OK);
	}

	@GetMapping("/get-user")
	public ResponseEntity<Person> findLdapPerson(@RequestParam(name = "user-id") String userId) {
		return new ResponseEntity<>(personRepo.getPersonNamesByUid(userId), HttpStatus.OK);
	}
	
	@PostMapping("/add-user")
	public ResponseEntity<String> bindLdapPerson(@RequestBody Person person) {
		String result = personRepo.create(person);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/update-user")
	public ResponseEntity<String> rebindLdapPerson(@RequestBody Person person) {
		String result = personRepo.update(person);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/retrieve-users")
	public ResponseEntity<List<Person>> retrieve() {
		return new ResponseEntity<List<Person>>(personRepo.retrieve(), HttpStatus.OK);
	}

	@GetMapping("/remove-user")
	public ResponseEntity<String> unbindLdapPerson(@RequestParam(name = "userId") String userId) {
		String result = personRepo.remove(userId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}