package com.example.demo.controller;

import com.example.demo.controller.dto.CadastroLivroDTO;
import com.example.demo.controller.dto.ErroResposta;
import com.example.demo.controller.mappers.AutorMapper;
import com.example.demo.controller.mappers.LivroMapper;
import com.example.demo.exceptions.RegistroDuplicadoException;
import com.example.demo.model.Livro;
import com.example.demo.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController{

    @Autowired
    private final LivroService livroService;

    private final LivroMapper livroMapper;

    @PostMapping
    public ResponseEntity<Object> salvar (@Valid @RequestBody CadastroLivroDTO dto){
        try{
            Livro livro = livroMapper.toEntity(dto);


            return ResponseEntity.ok(livro);
        } catch (RegistroDuplicadoException e) {
            var erroDto = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
    }



}
