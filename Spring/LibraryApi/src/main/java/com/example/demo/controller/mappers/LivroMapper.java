package com.example.demo.controller.mappers;

import com.example.demo.controller.dto.CadastroLivroDTO;
import com.example.demo.model.Livro;
import com.example.demo.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public abstract class LivroMapper {


     protected AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null))")
    public abstract Livro toEntity(CadastroLivroDTO dto);
}
