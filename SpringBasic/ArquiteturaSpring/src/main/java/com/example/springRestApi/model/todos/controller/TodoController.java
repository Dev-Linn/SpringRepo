package com.example.springRestApi.model.todos.controller;

import com.example.springRestApi.model.todos.TodoEntity;
import com.example.springRestApi.model.todos.service.TodoService;
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
        return todoService.salvar(novoTodo);
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
