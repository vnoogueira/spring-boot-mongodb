package com.vitorferreira.springbootmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.vitorferreira.springbootmongodb.domain.Post;
import com.vitorferreira.springbootmongodb.domain.User;
import com.vitorferreira.springbootmongodb.dto.AuthorDto;
import com.vitorferreira.springbootmongodb.repository.PostRepository;
import com.vitorferreira.springbootmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat date = new SimpleDateFormat("dd/mm/yyyy");
		date.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User jorge = new User(null, "Jorge Blue", "jorge@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, jorge));

		Post post1 = new Post(null, date.parse("01/01/2020"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDto(maria));
		Post post2 = new Post(null, date.parse("02/01/2020"), "Bom dia", "Acordei feliz hoje!", new AuthorDto(maria));

		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
		

	}

}
