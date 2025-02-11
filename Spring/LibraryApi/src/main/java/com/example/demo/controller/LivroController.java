package com.example.demo.controller;

import com.example.demo.controller.GenericController;
import com.example.demo.controller.dto.CadastroLivroDTO;
import com.example.demo.controller.dto.ErroResposta;
import com.example.demo.controller.mappers.LivroMapper;
import com.example.demo.exceptions.RegistroDuplicadoException;
import com.example.demo.model.Livro;
import com.example.demo.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@Valid @RequestBody CadastroLivroDTO dto) {
        try {
            Livro livro = livroMapper.toEntity(dto);
            livroService.save(livro);
            var url = gerarHeaderLocation(livro.getId());
            return ResponseEntity.accepted().location(url).build();
        } catch (RegistroDuplicadoException e) {
            var erroDto = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
    }
}
