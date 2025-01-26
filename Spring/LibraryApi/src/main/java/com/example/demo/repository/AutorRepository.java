package com.example.demo.repository;

import com.example.demo.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

    @Query("SELECT a FROM Autor a")
     List<Autor> ListarTodosAutores();

     List<Autor>findByNome(String nome);
     List<Autor>findByNacionalidade(String nacionalidade);
     List<Autor>findByNomeAndNacionalidade(String nome, String nacionalidade);
}
