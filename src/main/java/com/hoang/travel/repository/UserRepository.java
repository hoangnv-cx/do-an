package com.hoang.travel.repository;

import com.hoang.travel.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByUserName(String userName);
    UserEntity findByEmail(String email);
}
