package com.example.demo.controller.mappers;

import com.example.demo.controller.dto.AutorDTO;
import com.example.demo.model.Autor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorDTO dto);
    AutorDTO toDto(Autor autor);
}
