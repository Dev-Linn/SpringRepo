package com.example.springRestApi.model.todos.service;

import com.example.springRestApi.model.todos.TodoEntity;
import com.example.springRestApi.model.todos.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoEntity salvar(TodoEntity novoTodo){
        return todoRepository.save(novoTodo);
    }

    public void atualizarStatus(TodoEntity todo) {
        todoRepository.save(todo);
    }

    public TodoEntity findById(Integer id){
        return todoRepository.findById(id).orElse(null);
    }
}
