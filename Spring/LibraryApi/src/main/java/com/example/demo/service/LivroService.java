package com.example.demo.service;
import com.example.demo.model.Autor;
import com.example.demo.model.Livro;
import com.example.demo.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LivroService {


    private final LivroRepository livroRepository;

    public Livro save(Livro livro) {
        return livroRepository.save(livro);
    }

}
