package com.ach20raf.webservice_1.controllers;

import com.ach20raf.webservice_1.requests.UserRequest;
import com.ach20raf.webservice_1.responses.UserResponse;
import com.ach20raf.webservice_1.services.UserService;
import com.ach20raf.webservice_1.shared.dto.UserDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users") 
public class UserController {
    @Autowired
    private UserService userService ;
    @GetMapping
    public String getUser() {
        return "get User was called";
    }
    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        UserResponse response = new UserResponse();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);
        UserDto createUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createUser, response);
        return response;
    }
    @PutMapping
    public String updateUser() {
        return "update User was called";
    }
    @DeleteMapping
    public String deleteUser() {
        return "delete User was called";
    }
}
