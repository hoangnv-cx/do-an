package com.hoang.travel.service.impl;

import com.hoang.travel.entity.RoleEntity;
import com.hoang.travel.entity.UserEntity;
import com.hoang.travel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByUserName(username);
        if (entity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<RoleEntity> roleEntities = entity.getRoles();
        List<String> roleCode = new ArrayList<>();
        for (RoleEntity role : roleEntities) {
            roleCode.add(role.getRoleCode());
        }
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleCode != null) {
            for (String role : roleCode) {
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role);
                grantList.add(authority);
            }
        }
        UserDetails userDetails = (UserDetails) new User(entity.getUserName(),
                entity.getPassWord(), grantList);
        return userDetails;
    }
}
