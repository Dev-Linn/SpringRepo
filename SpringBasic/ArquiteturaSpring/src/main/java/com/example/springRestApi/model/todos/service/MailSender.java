package com.example.springRestApi.model.todos.service;


import org.springframework.stereotype.Component;

@Component
public class MailSender {

    public void send(String mensagem) {
        System.out.println("Enviando o email: " + mensagem);

    }
}
