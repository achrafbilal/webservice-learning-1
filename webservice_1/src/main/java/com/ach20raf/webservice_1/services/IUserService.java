package com.ach20raf.webservice_1.services;

import com.ach20raf.webservice_1.shared.dto.UserDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService  extends UserDetailsService{
    UserDto createUser(UserDto userDto);
}





