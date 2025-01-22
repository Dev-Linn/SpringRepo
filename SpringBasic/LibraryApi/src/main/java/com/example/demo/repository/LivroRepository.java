package com.example.demo.repository;

import com.example.demo.model.Autor;
import com.example.demo.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    // QUERY METOD
    // select * from where id_autor = id
    List<Livro> findByAutor(Autor autor);
}
