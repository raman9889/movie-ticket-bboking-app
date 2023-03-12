package com.xyz.booktickets.userservice.controller;

import com.xyz.booktickets.exception.UserNotFoundException;
import com.xyz.booktickets.userservice.dto.UserDto;
import com.xyz.booktickets.userservice.model.User;
import com.xyz.booktickets.userservice.repository.UserRepository;
import com.xyz.booktickets.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<User> getUser(@PathVariable("userName") String userName) {
        try {
            User _user = userService.getUser(userName);

            return new ResponseEntity<>(_user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto user) {
        try {
            User _user = userService.createUser(user);

            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
