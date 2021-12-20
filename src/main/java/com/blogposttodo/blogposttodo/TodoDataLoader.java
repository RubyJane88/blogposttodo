package com.blogposttodo.blogposttodo;


import com.blogposttodo.blogposttodo.config.todo.entity.TodoEntity;
import com.blogposttodo.blogposttodo.config.todo.repository.TodoRepository;
import com.blogposttodo.blogposttodo.user.entity.AddressEntity;
import com.blogposttodo.blogposttodo.user.entity.CompanyEntity;
import com.blogposttodo.blogposttodo.user.entity.GeoEntity;
import com.blogposttodo.blogposttodo.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TodoDataLoader implements CommandLineRunner {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void run(String... args) throws Exception {
        loadTodoData();
    }

    public TodoDataLoader(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }



    private void loadTodoData() {
          if (todoRepository.count() == 0) {


              var geo = new GeoEntity(UUID.randomUUID(), "37.762", "-122.446");
              var address = new AddressEntity(UUID.randomUUID(), "Kulas1", "Apt 556", "Gwenborough", "92998-3874", "NY", geo);
              var company = new CompanyEntity(UUID.randomUUID(), "Demagoguery-Cron2", "Multi-layered client-server neural-net2", "1-660-555-8808");
              var user = new UserEntity(UUID.randomUUID(), "Leane", "Bret", "since@gmail.com", address, company, "123456789", "yahoo.com");

              var todo1 = new TodoEntity(UUID.randomUUID(), "Buy a box of milk", false, user);
              todoRepository.save(todo1);
              var todo2 = new TodoEntity(UUID.randomUUID(), "Buy oranges", false, user);
              todoRepository.save(todo2);
              var todo3 = new TodoEntity(UUID.randomUUID(), "Buy bread", false, user);
              todoRepository.save(todo3);
              var todo4 = new TodoEntity(UUID.randomUUID(), "Buy eggs", false, user);
              todoRepository.save(todo4);
              var todo5 = new TodoEntity(UUID.randomUUID(), "Buy meat", false, user);
              todoRepository.save(todo5);

          }
        System.out.println(todoRepository.count());

    }



}
