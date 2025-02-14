package com.example.demo.controller.mappers;

import com.example.demo.controller.dto.CadastroLivroDTO;
import com.example.demo.controller.dto.ResultadoPesquisaLivroDTO;
import com.example.demo.model.Livro;
import com.example.demo.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

//LIVROS MAPPER
@Mapper(componentModel = "spring", uses = AutorMapper.class)
public abstract class LivroMapper {


    @Autowired
      AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null))")
    public abstract Livro toEntity(CadastroLivroDTO dto);

    public abstract ResultadoPesquisaLivroDTO toDto(Livro livro);

}
