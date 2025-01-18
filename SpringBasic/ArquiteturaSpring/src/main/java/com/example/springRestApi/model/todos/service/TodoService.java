package com.example.springRestApi.model.todos.service;

import com.example.springRestApi.model.todos.TodoEntity;
import com.example.springRestApi.model.todos.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private TodoRepository todoRepository;
    private Validator validator;
    private MailSender mailSender;

    public TodoService(TodoRepository todoRepository, Validator validator, MailSender mailSender) {
        this.todoRepository = todoRepository;
        this.validator = validator;
        this.mailSender = mailSender;
    }

    public TodoEntity salvar(TodoEntity novoTodo){

        validator.validar(novoTodo);
        return todoRepository.save(novoTodo);
    }

    public void atualizarStatus(TodoEntity todo) {
        todoRepository.save(todo);
        String status = todo.isConcluido() == Boolean.TRUE ? "concluido" : "não concluido";
        mailSender.send("Todo" + todo.getDescription() + "foi atualizado para" + status);
    }

    public TodoEntity findById(Integer id){
        return todoRepository.findById(id).orElse(null);
    }
}
