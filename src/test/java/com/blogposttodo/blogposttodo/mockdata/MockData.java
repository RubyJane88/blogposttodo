package com.blogposttodo.blogposttodo.mockdata;

import com.blogposttodo.blogposttodo.todo.entity.TodoEntity;
import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.lang.reflect.Type;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MockData {

    public static List<TodoEntity> getTodos() throws IOException {
        InputStream inputStream = Resources.getResource("todos.json").openStream();
        String json = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        Type listType = new TypeToken<ArrayList<TodoEntity>>() {
        }.getType();
        return new Gson().fromJson(json, listType);
    }
}
