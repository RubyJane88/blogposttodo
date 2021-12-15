package com.blogposttodo.blogposttodo.user.dto;

//import com.blogposttodo.blogposttodo.user.entity.AddressEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Integer id;
    private String name;
    private String userName;
    private String email;
//    private AddressEntity address;
//    private GeoEntity geo;
//    private CompanyEntity company;
    private String phone;
    private String website;

}
