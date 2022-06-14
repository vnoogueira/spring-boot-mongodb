package com.vitorferreira.springbootmongodb.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitorferreira.springbootmongodb.domain.User;

@RestController //Metoto do Spring para informar que é um metodo REST
@RequestMapping(value = "/users") // Identificando que será chamado pela url users
public class UserResource {

	@GetMapping //Metodo Get
	public ResponseEntity<List<User>> findAll() {
		List<User> list = new ArrayList<>(); //Instanciando o list sendo uma lista de arrays do tipo List<User>
		User maria = new User("1", "Maria Brown", "maria@google.com");
		User alex = new User("2", "Alex Green", "alex@google.com");
		list.addAll(Arrays.asList(maria, alex)); //salvando os usuarios maria e alex  
		return ResponseEntity.ok().body(list); //Retornando os usuarios do list com status http 200-OK
	}

}
