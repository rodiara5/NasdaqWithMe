package com.example.nasdaq.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.nasdaq.model.DTO.UserDto;

import lombok.AllArgsConstructor;

// Spring Security가 사용할 수 있는 전용 Dto(UserDetails)를 정의함!!
// 우리가 만든 UserDto를 이용하여 UserDetails를 정의함!!
@AllArgsConstructor
public class AuthUserDto implements UserDetails {

    private UserDto userDto;

    // 권한(들)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new GrantedAuthority() {
            
            @Override
            public String getAuthority() {
                return userDto.getUserRole();
            }
        });
        
        return authorities;
    }

    @Override
    public String getPassword() {
        return userDto.getUserPw();
    }

    @Override
    public String getUsername() {
        return userDto.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 유무 확인 
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠긴 유무 확인
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 계정 비번 오래 사용했는지 유무 확인 
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 활성화된 계정인지 유무 확인  
        return true;
    }
    
}
