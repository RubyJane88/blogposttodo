package com.blogposttodo.blogposttodo.user.controller;

import com.blogposttodo.blogposttodo.user.dto.FlattenUserDto;
import com.blogposttodo.blogposttodo.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/flatten-users")
public class FlattenUserController {

    private UserService userService;
    private ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<FlattenUserDto>> getAllFlattenUsers() {
        var flattenUsers = new ArrayList<FlattenUserDto>();

        userService.findAllUser().forEach(user -> {
            var flattenUserDto = new FlattenUserDto(); // create an instance here

            flattenUserDto.setId(user.getId());
            flattenUserDto.setName(user.getName());
            flattenUserDto.setUserName(user.getUserName());
            flattenUserDto.setEmail(user.getEmail());
            flattenUserDto.setPhone(user.getPhone());
            flattenUserDto.setWebsite(user.getWebsite());
            flattenUserDto.setStreet(user.getAddress().getStreet());
            flattenUserDto.setSuite(user.getAddress().getSuite());
            flattenUserDto.setCity(user.getAddress().getCity());
            flattenUserDto.setZipcode(user.getAddress().getZipcode());
            flattenUserDto.setLat(user.getAddress().getGeo().getLat());
            flattenUserDto.setLng(user.getAddress().getGeo().getLng());
            flattenUserDto.setCompanyName(user.getCompany().getName());
            flattenUserDto.setCatchPhrase(user.getCompany().getCatchPhrase());
            flattenUserDto.setBs(user.getCompany().getBs());

            flattenUsers.add(flattenUserDto); // push the instance after manually mapping everything
        });

        return new ResponseEntity<>(flattenUsers, HttpStatus.OK);
    }
}

