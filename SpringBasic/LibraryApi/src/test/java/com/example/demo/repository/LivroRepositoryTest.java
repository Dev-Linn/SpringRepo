package com.example.demo.repository;


import com.example.demo.model.Autor;
import com.example.demo.model.GeneroLivro;
import com.example.demo.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarLivro() throws Exception {
        Livro livro = new Livro();
        livro.setIsbn("0552");
        livro.setTitulo("Lincoln Livro");
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setPreco(BigDecimal.valueOf(100.00));
        livro.setDataPublicacao(LocalDate.of(2005,3,14));

        UUID id = UUID.fromString("84667f8d-e433-4d0a-8fc0-0c09be4a93cd");
        Optional<Autor> autor = autorRepository.findById(id);
        if (autor.isPresent()) {
            livro.setAutor(autor.get());
            livroRepository.save(livro);
        } else {
            throw new Exception("AUTOR NAO ENCONTRADO NO BANCO DE DADOS");
        }
    }

  @Test
    public void deletarLivro(){
        UUID id = UUID.fromString("9315d1fa-017e-464a-ab6a-13a2438a44f1");
        livroRepository.deleteById(id);
  }

  @Test
  public void buscarLivro(){
      UUID id = UUID.fromString("855ac5c7-ce4c-425f-915f-0d33fea344dd");
      Livro livro = livroRepository.findById(id).orElse(null);
      System.out.println("Livro:" + livro);
  }

  @Test
    public void atualizarLivro(){
        UUID id = UUID.fromString("855ac5c7-ce4c-425f-915f-0d33fea344dd");
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("a6ae3298-a580-4c37-950f-91c49fb32a0f");
        Autor autor = autorRepository.findById(idAutor).orElse(null);
        livroParaAtualizar.setAutor(autor);
        livroRepository.save(livroParaAtualizar);
    }

}