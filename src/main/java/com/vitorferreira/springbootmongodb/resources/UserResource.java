package com.vitorferreira.springbootmongodb.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vitorferreira.springbootmongodb.domain.User;
import com.vitorferreira.springbootmongodb.services.UserService;


@RestController // Metoto do Spring para informar que é um metodo REST
@RequestMapping(value = "/users") // Identificando que será chamado pela url users
public class UserResource {

	@Autowired
	private UserService service;

	@RequestMapping(method=RequestMethod.GET)// Metodo Get
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list); // Retornando os usuarios do list com status http 200-OK
	}
	
}
