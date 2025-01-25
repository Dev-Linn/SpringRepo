package com.example.demo.repository;

import com.example.demo.model.Autor;
import com.example.demo.model.GeneroLivro;
import com.example.demo.model.Livro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Rico");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2000, 1, 1));
        Autor save = repository.save(autor);
        System.out.println("Autor salvo: " + save);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("84667f8d-e433-4d0a-8fc0-0c09be4a93cd");

        Optional<Autor> possivelautor = repository.findById(id);

        if (possivelautor.isPresent()) {

            Autor autorEncontrado = possivelautor.get();
            System.out.println("Dados do autor:");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(2005, 3, 14));
            autorEncontrado.setNome("Lincoln");

            repository.save(autorEncontrado);
        } else {
            throw new RuntimeException("id: " + id + " NÃO ENCONTRADO NO BANCO DE DADOS");
        }

    }

    @Test
    public void deletarTest() {
        var id = UUID.fromString("15cebea0-396e-46b9-97e5-17335661f031");
        Optional<Autor> possivelautor = repository.findById(id);
        if (possivelautor.isPresent()) {
            var autorEncontrado = possivelautor.get();
            repository.delete(autorEncontrado);
        } else {
            throw new RuntimeException("id: " + id + " NÃO ENCONTRADO NO BANCO DE DADOS");
        }
    }

    @Test
    public void buscarTest() {
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("CONTAGEM DE AUTORES: " + repository.count());
    }

    @Test
    public void salvarAutorComLivros(){
        Autor autor = new Autor();
        autor.setNome("Wally");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2000, 1, 1));

        Livro livro = new Livro();
        livro.setIsbn("090909");
        livro.setTitulo("Wally Livro");
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setPreco(BigDecimal.valueOf(100.00));
        livro.setDataPublicacao(LocalDate.of(2005,3,14));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("2222");
        livro2.setTitulo("Wally Livro222");
        livro2.setGenero(GeneroLivro.BIOGRAFIA);
        livro2.setPreco(BigDecimal.valueOf(100.00));
        livro2.setDataPublicacao(LocalDate.of(2005,3,14));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);
        repository.save(autor);
        livroRepository.saveAll(autor.getLivros());

    }

    @Test
    public void buscarAutorComLivros(){
        var id = UUID.fromString("34e8700d-cf4b-415c-b995-369ccdc4b826");
        var autor = repository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);

    }

}
