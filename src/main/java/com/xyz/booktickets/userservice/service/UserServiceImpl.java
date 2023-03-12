package com.xyz.booktickets.userservice.service;

import com.xyz.booktickets.exception.UserNotFoundException;
import com.xyz.booktickets.userservice.dto.UserDto;
import com.xyz.booktickets.userservice.model.User;
import com.xyz.booktickets.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(String userName) {
        Optional<User> _user = userRepository.findByUserNameEqualsIgnoreCase(userName);
        return _user.orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User createUser(UserDto user) {
        return userRepository.save(new User(user.getUserName(), user.getPassword(), user.getEmailId(),
                user.getUserType()));
    }
}
