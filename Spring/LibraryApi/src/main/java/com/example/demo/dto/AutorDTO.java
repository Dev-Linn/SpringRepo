package com.example.demo.dto;

import com.example.demo.model.Autor;

import java.time.LocalDate;

public record AutorDTO(

        String nome,
        LocalDate dataNascimento,
        String nacionalidade)
{

    public Autor mapearParaAutor(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }

}
