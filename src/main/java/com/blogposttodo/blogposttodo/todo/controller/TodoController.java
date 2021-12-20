package com.blogposttodo.blogposttodo.todo.controller;


import com.blogposttodo.blogposttodo.config.todo.dto.TodoDto;
import com.blogposttodo.blogposttodo.config.todo.entity.TodoEntity;
import com.blogposttodo.blogposttodo.config.todo.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/todos")
//@PreAuthorize("isAuthenticated()") // for authorization
public class TodoController {

    private final TodoService todoService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<TodoDto> getTodos() {
        //MapStruct, another dto mapper but not that straightforward, is used to map the domain object to the DTO
        var todoList = StreamSupport.stream(todoService.findAllTodos().spliterator(), false).toList();

        return todoList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public TodoDto getTodoById(@PathVariable("id") UUID id) {
        return convertToDto(todoService.findTodoById(id));

    }

    @DeleteMapping("/{id}")
    public void deleteTodoById(@PathVariable("id") UUID id) {
        todoService.deleteTodo(id);
    }

    @PostMapping
    public TodoDto createTodo(@Valid @RequestBody TodoDto todoDto) {
        var todoEntity = convertToEntity(todoDto);
        var savedTodoEntity = todoService.createTodo(todoEntity);

        return convertToDto(todoService.createTodo(savedTodoEntity));
    }

    @PutMapping("/{id}")
    public void updateTodo(@PathVariable("id") UUID id, @Valid @RequestBody TodoDto todoDto) {
        if (!id.equals(todoDto.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id in path and id in body must match");

        var todoEntity = convertToEntity(todoDto);
        todoService.updateTodo(id, todoEntity);
    }


    private TodoDto convertToDto(TodoEntity todoEntity) {
        return modelMapper.map(todoEntity, TodoDto.class);
    }

    private TodoEntity convertToEntity(TodoDto todoDto) {
        return modelMapper.map(todoDto, TodoEntity.class);
    }


}
