package com.example.springRestApi.model.todos.controller;

import com.example.springRestApi.model.todos.TodoEntity;
import com.example.springRestApi.model.todos.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public TodoEntity save(@RequestBody TodoEntity novoTodo){
        try {
            return todoService.salvar(novoTodo);
        } catch (IllegalArgumentException e) {
            var mensagemErro = e.getMessage();
            throw new IllegalArgumentException(String.valueOf(HttpStatus.BAD_REQUEST));
        }

    }

    @PutMapping("{id}")
    public void atualizarStatus(@PathVariable("id") Integer id, @RequestBody TodoEntity todo){
        todo.setId(id);
        todoService.atualizarStatus(todo);
    };

    @GetMapping("{id}")
    public TodoEntity findById(@PathVariable Integer id) {
        return todoService.findById(id);
    }

}
