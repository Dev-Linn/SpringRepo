package com.example.springRestApi.model.todos.service;

import com.example.springRestApi.model.todos.TodoEntity;
import com.example.springRestApi.model.todos.repository.TodoRepository;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    TodoRepository todoRepository;

    public Validator(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void validar(TodoEntity todo) {
            if (existeTodoComDescricao(todo.getDescription())){
                throw new IllegalArgumentException("Já existe um TODO com essa descrição");
            }
    }

    private boolean existeTodoComDescricao(String description){
        return todoRepository.existsByDescription(description);
    }
}
