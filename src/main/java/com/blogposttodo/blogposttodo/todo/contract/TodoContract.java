package com.blogposttodo.blogposttodo.config.todo.contract;

import com.blogposttodo.blogposttodo.config.todo.entity.TodoEntity;

import java.util.UUID;

public interface TodoContract {

    Iterable<TodoEntity> findAllTodos();

    TodoEntity findTodoById(UUID id);

    TodoEntity createTodo(TodoEntity todo);

    void deleteTodo(UUID id);

    void updateTodo(UUID id, TodoEntity todo);
}


