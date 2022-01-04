package com.blogposttodo.blogposttodo.todo.service;


import com.blogposttodo.blogposttodo.todo.entity.TodoEntity;
import com.blogposttodo.blogposttodo.exception.NotFoundException;
import com.blogposttodo.blogposttodo.todo.contract.TodoContract;
import com.blogposttodo.blogposttodo.todo.repository.TodoRepository;
import com.blogposttodo.blogposttodo.todo.repository.TodoRepository;
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
        findOrThrow(id);
        todoRepository.save(todo);

    }

    private TodoEntity findOrThrow(final UUID id) {
        return todoRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Todo by id" + id + "not found"));
    }
}
