package com.example.demo.repository;

import com.example.demo.model.Autor;
import com.example.demo.model.GeneroLivro;
import com.example.demo.model.Livro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @see LivroRepositoryTest
 */


public interface LivroRepository extends JpaRepository<Livro, UUID> {

    // QUERY METOD
    // select * from where id_autor = id
    List<Livro> findByAutor(Autor autor);
    List<Livro> findByTitulo(String titulo);
    List<Livro> findByIsbn(String isbn);
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);
    List<Livro> findByTituloLike(String titulo);



    //select l.*from livro as l order by l.titulo
    @Query("select l from Livro as l order by l.titulo, l.preco")
    List<Livro> OrdernadoPorTituloEpreco();

    @Query("select a from Livro l join l.autor a")
    List<Autor> listarAutores();

    @Query("""
        select l.genero
        from Livro l
        join l.autor a
        where a.nacionalidade = 'Brasileira'
        order by l.genero
        """)
    List<String> ListarGenerosBrasileiros();

    @Query("select l from Livro l where l.genero = :genero order by :paramOrdenacao")
    List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro, @Param("paramOrdenacao") String propriedade);

    @Modifying
    @Transactional
    @Query(" delete from Livro where genero = ?1")
    void deleteByGenero(GeneroLivro generoLivro);

    boolean existsByAutor(Autor autor);

}
