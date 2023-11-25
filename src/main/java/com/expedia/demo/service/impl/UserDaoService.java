package com.expedia.demo.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.expedia.demo.service.UserService;
import com.expedia.demo.model.User;

public class UserDaoService implements UserService {
	// JPA/Hibernate > Database
	// UserDaoService > Static List
	
	private static List<User> users = new ArrayList<>();
	
	private static int usersCount = 0;
	
	static {
		users.add(new User(++usersCount,"Adam",LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount,"Eve",LocalDate.now().minusYears(25)));
		users.add(new User(++usersCount,"Jim",LocalDate.now().minusYears(20)));
	}
	@Override
	public List<User> findAll() {
		return users;
	}
	@Override
	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
	@Override
	public Optional<User> findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id); 
		return Optional.of(users.stream().filter(predicate).findFirst().orElse(null));
	}
	@Override
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}

	
}
