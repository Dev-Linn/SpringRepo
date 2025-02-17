package com.example.demo.service;
import com.example.demo.model.Autor;
import com.example.demo.model.Livro;
import com.example.demo.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class LivroService {


    private final LivroRepository livroRepository;

    public Livro save(Livro livro) {
        return livroRepository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id){
        return livroRepository.findById(id);
    }

    public void delete(Livro livro) {
        livroRepository.delete(livro);
    }

}
