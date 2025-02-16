package com.example.demo.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;


//teste ubuntu
public interface GenericController {

    default URI gerarHeaderLocation(UUID id){
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
