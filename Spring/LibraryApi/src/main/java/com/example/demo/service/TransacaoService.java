package com.example.demo.service;

import com.example.demo.model.Autor;
import com.example.demo.model.GeneroLivro;
import com.example.demo.model.Livro;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizacaoSemSalvar(){
        var livro = livroRepository.findById(UUID.fromString("27e080bb-e37e-4cf1-8c43-1571ac2e71ea")).orElse(null);
        livro.setDataPublicacao(LocalDate.now());

    }

    @Transactional
    public void executar(){
        Autor autor = new Autor();
        autor.setNome("francis");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1951, 1, 1));
        autorRepository.save(autor);


        Livro livro = new Livro();
        livro.setIsbn("0001");
        livro.setTitulo("Livro do jose");
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setPreco(BigDecimal.valueOf(100.00));
        livro.setDataPublicacao(LocalDate.of(2005,3,14));
        livro.setAutor(autor);
        livroRepository.save(livro);


        if(autor.getNome().equals("jose")){
            throw new RuntimeException("Rollback");
        }
    }

}
