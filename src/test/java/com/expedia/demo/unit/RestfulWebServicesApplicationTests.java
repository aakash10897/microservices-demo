package com.expedia.demo.unit;

import com.expedia.demo.model.Post;
import com.expedia.demo.model.User;
import com.expedia.demo.repository.UserRepository;
import com.expedia.demo.service.impl.UserJpaService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@RunWith(SpringRunner.class)
public class RestfulWebServicesApplicationTests {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserJpaService userJpaService;

	@Test
	public void setUserJpaService_saveUserTest(){
		var postRequest = List.of(Post.builder().description("HelloWorld").build());
		var userRequest = User.builder().name("Aakash").posts(postRequest).birthDate(LocalDate.now().minus(1, ChronoUnit.DAYS)).build();
		var postResponse = List.of(Post.builder().id(1).description("HelloWorld").build());
		var userResponse = User.builder().name("Aakash").posts(postResponse).birthDate(LocalDate.now().minus(1, ChronoUnit.DAYS)).build();
		Mockito.when(userRepository.save(userRequest)).thenReturn(userResponse);
		var savedUser = userJpaService.save(userRequest);
		Assertions.assertEquals(userResponse.getName(), savedUser.getName());
	}

}
