package com.example.demo.repository;

import com.example.demo.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Linkin");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 1));
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
        var id = UUID.fromString("84bd3aed-752e-4869-97b7-128898fbabbe");
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

}
