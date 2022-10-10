package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JpaUserDetailsService implements UserDetailsService
{
    //=========================
    //=      Attributes       =
    //=========================
    private final UserRepository userRepository;


    //=========================
    //=     Constructors      =
    //=========================
    public JpaUserDetailsService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    //=========================
    //=   Business Method     =
    //=========================
    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException
    {

        User user = userRepository.findByUsername(username)
                                  .orElseThrow(() -> new UsernameNotFoundException("Problem during authentication!"));

        return new SecurityUser(user);
    }
}