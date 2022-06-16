package com.vitorferreira.springbootmongodb.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vitorferreira.springbootmongodb.domain.User;
import com.vitorferreira.springbootmongodb.dto.UserDto;
import com.vitorferreira.springbootmongodb.services.UserService;


@RestController // Metoto do Spring para informar que é um metodo REST
@RequestMapping(value = "/users") // Identificando que será chamado pela url users
public class UserResource {

	@Autowired
	private UserService service;

	@RequestMapping(method=RequestMethod.GET)// Metodo Get
	public ResponseEntity<List<UserDto>> findAll() {
		List<User> list = service.findAll();
		List<UserDto> listDto = list.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto); // Retornando os usuarios do list com status http 200-OK
	}
	
}
