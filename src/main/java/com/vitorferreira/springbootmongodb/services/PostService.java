package com.vitorferreira.springbootmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitorferreira.springbootmongodb.domain.Post;
import com.vitorferreira.springbootmongodb.repository.PostRepository;
import com.vitorferreira.springbootmongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public List<Post> findAll() {
		return repo.findAll();
		
	}
	
	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

}
