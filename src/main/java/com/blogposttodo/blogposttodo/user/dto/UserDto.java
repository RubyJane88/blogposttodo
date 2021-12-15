package com.blogposttodo.blogposttodo.user.dto;

import com.blogposttodo.blogposttodo.user.entity.AddressEntity;
import com.blogposttodo.blogposttodo.user.entity.CompanyEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {
    private UUID id;
    private String name;
    private String userName;
    private String email;
    private AddressEntity address;
    private String phone;
    private String website;
    private CompanyEntity company;


}
