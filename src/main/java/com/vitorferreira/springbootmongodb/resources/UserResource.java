package com.vitorferreira.springbootmongodb.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vitorferreira.springbootmongodb.domain.Post;
import com.vitorferreira.springbootmongodb.domain.User;
import com.vitorferreira.springbootmongodb.dto.UserDto;
import com.vitorferreira.springbootmongodb.services.UserService;

@RestController // Metoto do Spring para informar que é um metodo REST
@RequestMapping(value = "/users") // Identificando que será chamado pela url users
public class UserResource {

	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET) // Metodo Get
	public ResponseEntity<List<UserDto>> findAll() {
		List<User> list = service.findAll();
		List<UserDto> listDto = list.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto); // Retornando os usuarios do list com status http 200-OK
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDto> findById(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDto(obj));
	}
	
	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findPost(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDto objDto) {
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDto objDto, @PathVariable String id) {
		User obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}

}
