package com.hoang.travel.repository;

import com.hoang.travel.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
    RoleEntity findByRoleName(String name);
}
