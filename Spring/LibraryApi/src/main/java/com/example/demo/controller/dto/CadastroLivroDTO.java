package com.example.demo.controller.dto;

import com.example.demo.model.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CadastroLivroDTO(String isbn, String titulo,
                               LocalDate dataPublicacao,
                               GeneroLivro genero,
                               BigDecimal preco,
                               AutorDTO autor) {
}
