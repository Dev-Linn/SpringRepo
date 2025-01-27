package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table
@ToString(exclude = "autor")
public class Livro {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    @Id private UUID id;

    @Column(name = "isbn", nullable = false, length = 20)
    private String isbn;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING) @Column(name = "genero", nullable = false)
    private GeneroLivro genero;

    @Column(name= "preco", precision = 18, scale = 2)
    private BigDecimal preco;

    @ManyToOne @JsonIgnore
    @JoinColumn(name = "id_autor")
    private Autor autor;
}
