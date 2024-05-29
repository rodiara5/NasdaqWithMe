package com.example.nasdaq.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private String userId;
    private String userName;
    private String userPw;
    private String userEmail;
    private String userRole;
    private Boolean isLogin;
}

