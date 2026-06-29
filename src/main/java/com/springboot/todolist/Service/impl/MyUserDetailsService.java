package com.springboot.todolist.Service.impl;

import com.springboot.todolist.Entity.UserPrincipal;
import com.springboot.todolist.Entity.Users;
import com.springboot.todolist.Reposiytory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username);

        if(user == null ){
            throw new UsernameNotFoundException("user not found");
        }


        return new UserPrincipal(user);
    }
}
