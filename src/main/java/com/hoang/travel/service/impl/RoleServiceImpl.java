package com.hoang.travel.service.impl;

import com.hoang.travel.entity.RoleEntity;
import com.hoang.travel.entity.UserEntity;
import com.hoang.travel.repository.RoleRepository;
import com.hoang.travel.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleEntity addAndUpdateUser(RoleEntity roleEntity) {
        return roleRepository.save(roleEntity);
    }

    @Override
    public RoleEntity findByID(int id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public List<RoleEntity> findAll() {
        return (List<RoleEntity>) roleRepository.findAll();
    }

    @Override
    public List<RoleEntity> findAllRole() {
        return (List<RoleEntity>) roleRepository.findAll();
    }

    @Override
    public RoleEntity findByName(String name) {
        return roleRepository.findByRoleName(name);
    }
}
