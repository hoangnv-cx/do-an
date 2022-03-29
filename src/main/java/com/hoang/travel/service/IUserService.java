package com.hoang.travel.service;
import com.hoang.travel.entity.UserEntity;

import java.util.List;

public interface IUserService {
    List<UserEntity> findAll();
    UserEntity findByName();
    UserEntity addAndUpdateUser(UserEntity userEntity);
    UserEntity findUserByID(int id);
    void deleteUser(int id);
    boolean userNameExists(String userName);

}
