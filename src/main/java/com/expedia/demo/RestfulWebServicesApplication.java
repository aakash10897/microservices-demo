package com.expedia.demo;

import com.expedia.demo.repository.PostRepository;
import com.expedia.demo.repository.UserRepository;
import com.expedia.demo.service.impl.PostJpaService;
import com.expedia.demo.service.impl.UserDaoService;
import com.expedia.demo.service.impl.UserJpaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestfulWebServicesApplication {

	@Bean
	public UserDaoService userDaoService(){
		return new UserDaoService();
	}

	@Bean
	public UserJpaService userJpaService(final UserRepository userRepository){
		return new UserJpaService(userRepository);
	}

	@Bean
	public PostJpaService postJpaService(final PostRepository postRepository){
		return new PostJpaService(postRepository);
	}
	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

}
