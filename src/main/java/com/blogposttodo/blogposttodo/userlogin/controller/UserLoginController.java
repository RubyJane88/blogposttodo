package com.blogposttodo.blogposttodo.userlogin.controller;


import com.blogposttodo.blogposttodo.userlogin.dto.UserLoginDto;
import com.blogposttodo.blogposttodo.userlogin.service.UserLoginService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Log4j2
@AllArgsConstructor
@RestController
@PreAuthorize("permitAll()")
public class UserLoginController {

    private final UserLoginService userLoginService;

    @GetMapping("/api/v1/userslogin")
    public Iterable<UserLoginDto> getUsersLogin() {
        return userLoginService.getAllUsersLogin();
    }

   @GetMapping("/api/v1/userslogin/{id}")
    public UserLoginDto getUserLogin(@PathVariable("id") UUID id) {
        return userLoginService.findUserLoginById(id);
    }

    @DeleteMapping("/api/v1/userslogin/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserLogin(@PathVariable("id") UUID id) {
        userLoginService.deleteUserLogin(id);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserLoginDto createUserLogin(@Valid @RequestBody UserLoginDto userLoginDto) throws NoSuchAlgorithmException {
        return userLoginService.createUserLogin(userLoginDto, userLoginDto.getPassword());
    }

    @PutMapping("/api/v1/userslogin/{id}")
    public void putUserLogin(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UserLoginDto userLoginDto
    ) throws NoSuchAlgorithmException {
        userLoginService.updateUserLogin(id, userLoginDto, userLoginDto.getPassword());
    }
}
