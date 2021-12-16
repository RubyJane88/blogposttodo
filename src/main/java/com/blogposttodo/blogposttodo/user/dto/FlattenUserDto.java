package com.blogposttodo.blogposttodo.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FlattenUserDto {
    private UUID id;
    private String name;
    private String userName; // username
    private String email;
    private String street;
    private String suite;
    private String city;
    private String state;
    private String zipcode;
    private String phone;
    private String website;
    private String lat;
    private String lng;
    private String companyName;
    private String catchPhrase;
    private String bs;
}
