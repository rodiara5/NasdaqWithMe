package com.example.nasdaq.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.nasdaq.model.DAO.UserDao;
import com.example.nasdaq.model.DTO.UserDto;
import com.example.nasdaq.model.Repository.UserRepository;
import com.example.nasdaq.service.UserService;



@Service
public class AuthUserService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        UserDto userDto = userService.getUserByName(name);

        // username의 데이터가 database에 존재함(가입함)!!
        if(userDto != null) {
            return new AuthUserDto(userDto);
        }
        

        // 존재하지 않음
        return null;
    }
    
}
