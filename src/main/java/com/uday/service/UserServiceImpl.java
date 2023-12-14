package com.uday.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uday.dto.UserDto;
import com.uday.model.User;
import com.uday.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(UserDto userDto) {

		User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), userDto.getRole(),
				userDto.getFullname());

		saveRoles(user);
		return userRepository.save(user);
	}

	public void saveRoles(User user) {
		user.setRole("USER");
	}
}
