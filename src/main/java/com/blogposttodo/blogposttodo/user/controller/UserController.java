package com.blogposttodo.blogposttodo.user.controller;

import com.blogposttodo.blogposttodo.user.dto.UserDto;
import com.blogposttodo.blogposttodo.user.entity.UserEntity;
import com.blogposttodo.blogposttodo.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private UserService userService;
    private ModelMapper mapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
          var userList = StreamSupport
                  .stream(userService.findAllUser().spliterator(), false).toList();


          return userList
                  .stream()
                  .map(this::convertToDto)
                  .collect(Collectors.toList());
    }



    private UserDto convertToDto(UserEntity entity) {
        return mapper.map(entity, UserDto.class);
    }

    private UserEntity convertToEntity(UserDto dto) {
        return mapper.map(dto, UserEntity.class);
    }
}
