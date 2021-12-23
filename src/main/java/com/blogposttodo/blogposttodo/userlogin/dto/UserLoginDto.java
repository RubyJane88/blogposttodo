package com.blogposttodo.blogposttodo.userlogin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    private UUID id;
    private String email;
    private String mobileNumber;
    private String password;
}
