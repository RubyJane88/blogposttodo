package com.blogposttodo.blogposttodo.config.todo.service;


import com.blogposttodo.blogposttodo.config.todo.entity.TodoEntity;
import com.blogposttodo.blogposttodo.exception.NotFoundException;
import com.blogposttodo.blogposttodo.config.todo.contract.TodoContract;
import com.blogposttodo.blogposttodo.config.todo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class TodoService implements TodoContract {

    private final TodoRepository todoRepository;

    @Override
    public Iterable<TodoEntity> findAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public TodoEntity findTodoById(UUID id) {
        return findOrThrow(id);
    }

    @Override
    public TodoEntity createTodo(TodoEntity todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void deleteTodo(UUID id) {
       todoRepository.deleteById(id);
    }

    @Override
    public void updateTodo(UUID id, TodoEntity todo) {

    }

    private TodoEntity findOrThrow (final UUID id) {
        return todoRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Todo by id" + id + "not found"));
    }
}
