package com.blogposttodo.blogposttodo.user.controller;

import com.blogposttodo.blogposttodo.user.entity.AddressEntity;
import com.blogposttodo.blogposttodo.user.entity.GeoEntity;
import com.blogposttodo.blogposttodo.user.entity.UserEntity;
import com.blogposttodo.blogposttodo.user.service.UserService;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private final Faker faker = new Faker();
    private List<UserEntity> userFixture;

    @BeforeEach
    public void setUp() {
        var user1 = new UserEntity();
        user1.setId(UUID.randomUUID());
        user1.setName(faker.name().name());
        user1.setUserName(faker.name().username());
        user1.setEmail(faker.internet().emailAddress());
        user1.setAddress(
                new AddressEntity(
                        UUID.randomUUID(),
                        faker.address().streetAddress(),
                        faker.address().streetAddressNumber(),
                        faker.address().city(),
                        faker.address().state(),
                        faker.address().zipCode(),
                        new GeoEntity(UUID.randomUUID(), faker.address().latitude(), faker.address().longitude())));

        var user2 = new UserEntity();
        user2.setId(UUID.randomUUID());
        user2.setName(faker.name().name());
        user2.setUserName(faker.name().username());
        user2.setEmail(faker.internet().emailAddress());
        user2.setAddress(
                new AddressEntity(
                        UUID.randomUUID(),
                        faker.address().streetAddress(),
                        faker.address().streetAddressNumber(),
                        faker.address().city(),
                        faker.address().state(),
                        faker.address().zipCode(),
                        new GeoEntity(UUID.randomUUID(), faker.address().latitude(), faker.address().longitude())));

        userFixture = List.of(user1, user2);
    }

    @Test
    void canReturnAllUsers() throws Exception {
        given(userService.findAllUser()).willReturn(userFixture);

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(userFixture.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].name").value(userFixture.get(0).getName()))
                .andExpect(jsonPath("$[0].userName").value(userFixture.get(0).getUserName()))
                .andExpect(jsonPath("$[0].email").value(userFixture.get(0).getEmail()))
                .andExpect(jsonPath("$[0].address.id").value(userFixture.get(0).getAddress().getId().toString()))
                .andExpect(jsonPath("$[0].address.street").value(userFixture.get(0).getAddress().getStreet()))
                .andExpect(jsonPath("$[0].address.city").value(userFixture.get(0).getAddress().getCity()))
                .andExpect(jsonPath("$[0].address.state").value(userFixture.get(0).getAddress().getState()))
                .andExpect(jsonPath("$[0].address.geo.id").value(userFixture.get(0).getAddress().getGeo().getId().toString()));


    }




}
