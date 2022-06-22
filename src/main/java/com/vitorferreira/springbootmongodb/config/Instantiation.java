package com.vitorferreira.springbootmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.aggregation.DateOperators.Timezone;

import com.vitorferreira.springbootmongodb.domain.Post;
import com.vitorferreira.springbootmongodb.domain.User;
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

		Post post1 = new Post(null, date.parse("01/01/2020"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", maria);
		Post post2 = new Post(null, date.parse("02/01/2020"), "Bom dia", "Acordei feliz hoje!", maria);

		userRepository.saveAll(Arrays.asList(maria, alex, jorge));
		postRepository.saveAll(Arrays.asList(post1, post2));

	}

}
