package com.expedia.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.expedia.demo.exception.UserNotFoundException;
import com.expedia.demo.model.Post;
import com.expedia.demo.model.User;
import com.expedia.demo.service.impl.PostJpaService;
import com.expedia.demo.service.impl.UserJpaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class UserJpaController {

	private UserJpaService userJpaService;
	
	private PostJpaService postJpaService;

	public UserJpaController(final UserJpaService userJpaService, final PostJpaService postJpaService){
		this.userJpaService = userJpaService;
		this.postJpaService = postJpaService;
	}

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userJpaService.findAll();
	}

	
	//http://localhost:8080/users
	
	//EntityModel
	//WebMvcLinkBuilder
	
	@GetMapping("/jpa/users/{id}")
	public ResponseEntity<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userJpaService.findOne(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		return ResponseEntity.ok(user.get());
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userJpaService.deleteById(id);
	}

	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id) {
		Optional<User> user = userJpaService.findOne(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		return user.get().getPosts();

	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		
		User savedUser = userJpaService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();   
		
		return ResponseEntity.created(location).build();
	}


	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @RequestBody Post post) {
		Optional<User> user = userJpaService.findOne(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		post.setUser(user.get());
		
		Post savedPost = postJpaService.savePost(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();   

		return ResponseEntity.created(location).build();

	}

	
}
