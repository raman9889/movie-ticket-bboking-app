package com.xyz.booktickets.userservice.service;

import com.xyz.booktickets.exception.UserNotFoundException;
import com.xyz.booktickets.userservice.dto.UserDto;
import com.xyz.booktickets.userservice.model.User;

import java.util.Optional;

public interface UserService {

    User getUser(String userName);

    User createUser(UserDto user);
}
