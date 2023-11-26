package com.expedia.demo.integration.test;

import com.expedia.demo.integration.App;
import com.expedia.demo.model.Post;
import com.expedia.demo.model.User;
import com.expedia.demo.service.impl.UserJpaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@SpringBootTest
public class UserJpaServiceTest extends App {

    @Autowired
    private UserJpaService userJpaService;
    @Test
    public void setUserJpaService_saveUserTest(){
        var post = List.of(Post.builder().description("HelloWorld").build());
        var user = User.builder().name("Aakash").posts(post).birthDate(LocalDate.now().minus(1, ChronoUnit.DAYS)).build();
        var saveResponse = userJpaService.save(user);
        Assertions.assertEquals(user.getName(), saveResponse.getName());
    }

}
