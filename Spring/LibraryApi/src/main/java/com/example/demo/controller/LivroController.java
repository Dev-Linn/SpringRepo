package com.example.demo.controller;

import com.example.demo.controller.GenericController;
import com.example.demo.controller.dto.CadastroLivroDTO;
import com.example.demo.controller.dto.ErroResposta;
import com.example.demo.controller.dto.ResultadoPesquisaLivroDTO;
import com.example.demo.controller.mappers.LivroMapper;
import com.example.demo.exceptions.OperacaoNaoPermitidaException;
import com.example.demo.exceptions.RegistroDuplicadoException;
import com.example.demo.model.Livro;
import com.example.demo.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;

    //ResponseEntity
    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody CadastroLivroDTO dto) {

        Livro livro = livroMapper.toEntity(dto);
        livroService.save(livro);
        var url = gerarHeaderLocation(livro.getId());
        return ResponseEntity.accepted().location(url).build();

    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(@PathVariable("id") String id) {
            return livroService.obterPorId(UUID.fromString(id)).map(livro -> {
                var dto = livroMapper.toDto(livro);
                return ResponseEntity.ok(dto);
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}



