package com.hoang.travel.service.impl;

import com.hoang.travel.entity.UserEntity;
import com.hoang.travel.repository.UserRepository;
import com.hoang.travel.service.IUserService;
import com.hoang.travel.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> findAll() {
        return (List<UserEntity>) userRepository.findAll();
    }

    @Override
    public UserEntity findByName() {

        return userRepository.findByUserName(SecurityUtils.getName());
    }

    @Override
    public boolean userNameExists(String userName) {
        return userRepository.findByUserName(userName) != null;
    }

    @Override
    public UserEntity addAndUpdateUser(UserEntity userEntity) {
        userEntity.setPassWord(passwordEncoder.encode(userEntity.getPassWord()));
        userEntity.setRoles(userEntity.getRoles());
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findUserByID(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}
