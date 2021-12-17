package com.blogposttodo.blogposttodo.user.service;

import com.blogposttodo.blogposttodo.user.dto.UserDto;
import com.blogposttodo.blogposttodo.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);

    }

    @Test
    void shouldReturnAllUser() {
        // Given
        // When
        userService.findAllUser();
        // Then
        verify(userRepository).findAll();
    }

}
