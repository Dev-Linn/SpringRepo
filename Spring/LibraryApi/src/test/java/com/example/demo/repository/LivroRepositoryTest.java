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
import java.util.List;
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
        livro.setIsbn("9999");
        livro.setTitulo("Livro Caro");
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setPreco(BigDecimal.valueOf(999.00));
        livro.setDataPublicacao(LocalDate.of(2005,3,14));

        UUID id = UUID.fromString("9430e638-7a46-4cfd-a5d5-fda1a23dbbf0");
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
        UUID id = UUID.fromString("f8758bba-86f3-48db-9a8c-101a141fe730");
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

    @Test
    public void buscarPorTitulo(){
        List<Livro> lista = livroRepository.findByTitulo("Lincoln Livro");
        lista.forEach(System.out::println);
    }

    @Test
    public void buscarPorIsbn(){
        List<Livro> lista = livroRepository.findByIsbn("0552");
        lista.forEach(System.out::println);
    }

    @Test
    public void buscarPorTituloAndPreco(){
        var preco = BigDecimal.valueOf(100.00);
        String titulo = "Lincoln Livro";
        List<Livro> lista = livroRepository.findByTituloAndPreco(titulo, preco);
        lista.forEach(System.out::println);
    }

    @Test
    public void buscarPorTituloLike(){
        String titulo = "L";
        List<Livro> lista = livroRepository.findByTituloLike("%" + titulo + "%");
        lista.forEach(System.out::println);
    }

    @Test
    public void OrdernadoPorTituloEprecoQueryJPQL(){

        List<Livro> lista = livroRepository.OrdernadoPorTituloEpreco();
        lista.forEach(System.out::println);
    }
    @Test
    public void ListarAutores(){

        List<Autor> lista = livroRepository.listarAutores();
        lista.forEach(System.out::println);
    }
    @Test
    public void ListarAutoresBrasileiros(){

        var resultado = livroRepository.ListarGenerosBrasileiros();
        resultado.forEach(System.out::println);
    }

    @Test
    public void ListarPorGeneroQueryJPQL(){
        var resultado = livroRepository.findByGenero(GeneroLivro.BIOGRAFIA, "dataPublicacao" );
        resultado.forEach(System.out::println);
    }

    @Test
    public void DeletarPorGeneroQueryJPQL(){
        livroRepository.deleteByGenero(GeneroLivro.BIOGRAFIA);
        System.out.println("Deletado com sucesso");

    }

    @Test
    public void ListarTodosLivros(){
        var resultado = livroRepository.findAll();
        resultado.forEach(System.out::println);
    }

}