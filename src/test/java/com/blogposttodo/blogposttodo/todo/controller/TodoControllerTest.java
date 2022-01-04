package com.blogposttodo.blogposttodo.todo.controller;

import com.blogposttodo.blogposttodo.mockdata.MockData;
import com.blogposttodo.blogposttodo.todo.entity.TodoEntity;
import com.blogposttodo.blogposttodo.todo.service.TodoService;
import com.blogposttodo.blogposttodo.user.entity.UserEntity;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @MockBean
    private TodoService todoService;

    @Autowired
    private MockMvc mockMvc;
    private List<TodoEntity> todosFixture;

    @BeforeEach()
    public void setUp() throws IOException {
//      todosFixture = MockData.getTodos();
    }

    @Test
    void getAllTodos() throws Exception {
//      given(todoService.findAllTodos()).willReturn(todosFixture);
//
//      mockMvc.perform(get("/api/v1/todos")).andExpect(status().isOk());
    }
}




