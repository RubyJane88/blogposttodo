package com.blogposttodo.blogposttodo.user.controller;

import com.blogposttodo.blogposttodo.user.dto.UserDto;
import com.blogposttodo.blogposttodo.user.entity.UserEntity;
import com.blogposttodo.blogposttodo.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.lang.module.ResolutionException;
import java.util.List;
import java.util.UUID;
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

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") UUID id) {
        return convertToDto(userService.findUserById(id));
    }

     @DeleteMapping("/{id}")
     public void deleteUser(@PathVariable("id") UUID id) {
         userService.deleteUser(id);
     }

     @PostMapping
     public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        var entity = convertToEntity(userDto);
         var savedEntity = userService.createUser(entity);
         return convertToDto(savedEntity);
     }

  @PutMapping("/{id}")
  public void putUser (@PathVariable("id") UUID id, @Valid @RequestBody UserDto userDto) {
        if (!id.equals(userDto.getId()))  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id in path and id in body must match");

        var entity = convertToEntity(userDto);
        userService.updateUser(id, entity);

  };



    private UserDto convertToDto(UserEntity entity) {
        return mapper.map(entity, UserDto.class);
    }

    private UserEntity convertToEntity(UserDto dto) {
        return mapper.map(dto, UserEntity.class);
    }
}
