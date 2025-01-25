package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@ToString(exclude = "livros")
@Entity
@Table(name="autor", schema = "public")
@EntityListeners(AuditingEntityListener.class)
public class Autor implements Serializable {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id @Column(name = "id") private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    String nome;
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;
    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;


    @OneToMany(mappedBy = "autor")
    private List<Livro> livros;

    @CreatedDate
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;
    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "id_usuario")
    private UUID idUsuario;

}
