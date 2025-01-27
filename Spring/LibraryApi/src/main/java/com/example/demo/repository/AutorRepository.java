package com.example.demo.repository;

import com.example.demo.model.Autor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

    @Query("SELECT a FROM Autor a")
     List<Autor> ListarTodosAutores();

     List<Autor>findByNomeLike(String nome);
     List<Autor>findByNacionalidadeLike(String nacionalidade);
     List<Autor>findByNomeLikeAndNacionalidade(String nome, String nacionalidade);

    Optional<Autor> findByNome(String nome);
}
