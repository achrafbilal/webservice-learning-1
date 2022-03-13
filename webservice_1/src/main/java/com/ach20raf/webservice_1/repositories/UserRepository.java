package com.ach20raf.webservice_1.repositories;

import com.ach20raf.webservice_1.entities.UserEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long>{
    UserEntity findByEmail(String email);
}
