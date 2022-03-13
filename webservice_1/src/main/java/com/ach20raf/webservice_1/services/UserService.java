package com.ach20raf.webservice_1.services;

import java.util.ArrayList;

import com.ach20raf.webservice_1.entities.UserEntity;
import com.ach20raf.webservice_1.repositories.UserRepository;
import com.ach20raf.webservice_1.shared.Utils;
import com.ach20raf.webservice_1.shared.dto.UserDto;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    Utils utils;
    @Override
    public UserDto createUser(UserDto userDto)
    {
        if(userRepository.findByEmail(userDto.getEmail()) !=null){
            throw new RuntimeException("User with email '"+userDto.getEmail()+"' already exist");
        }
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(userDto, entity);
        System.out.println(userDto.getPassword());
        entity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        entity.setUserId(utils.generateUserID(32));
        UserEntity createdEntity = userRepository.save(entity);
        UserDto createdDto=new UserDto();
        BeanUtils.copyProperties(createdEntity, createdDto);
        return createdDto;
        
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);

        if (user == null)

            throw new UsernameNotFoundException("No user with this email was found");
            
        return new User(user.getEmail(), user.getEncryptedPassword(),new ArrayList<>());
    }
}
