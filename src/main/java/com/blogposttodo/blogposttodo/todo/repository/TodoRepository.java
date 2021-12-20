package com.blogposttodo.blogposttodo.config.todo.repository;

import com.blogposttodo.blogposttodo.config.todo.entity.TodoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface TodoRepository extends CrudRepository<TodoEntity, UUID> {
  //@Query
  //public List<TodoEntity> findByUserId(UUID userId)
    //Custom composite repository query
    //public List<TodoEntity> findByUserIdAndCompleted(UUID userId, boolean completed);


}
