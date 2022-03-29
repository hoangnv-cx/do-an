package com.hoang.travel.service;

import com.hoang.travel.entity.RoleEntity;
import com.hoang.travel.entity.UserEntity;

import java.util.List;

public interface IRoleService {
    RoleEntity addAndUpdateUser(RoleEntity roleEntity);
    RoleEntity findByID(int id);
    List<RoleEntity> findAll();
    List<RoleEntity> findAllRole();
    RoleEntity findByName(String name);
}
