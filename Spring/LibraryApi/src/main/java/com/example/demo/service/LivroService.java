package com.example.demo.service;


import com.example.demo.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {

    @Autowired
    private final LivroRepository livroRepository;



}
