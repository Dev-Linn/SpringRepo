package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="autor", schema = "public")
public class Autor implements Serializable {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id @Column(name = "id") private UUID id;

    @Column(name = "nome", length = 100, nullable = false) private String nome;
    @Column(name = "data_nascimento", nullable = false) private LocalDate dataNascimento;
    @Column(name = "nacionalidade", length = 50, nullable = false) private String nacionalidade;


}
