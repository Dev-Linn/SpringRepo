package com.example.demo.controller;

import com.example.demo.controller.dto.AutorDTO;
import com.example.demo.controller.dto.ErroResposta;
import com.example.demo.controller.mappers.AutorMapper;
import com.example.demo.exceptions.OperacaoNaoPermitidaException;
import com.example.demo.exceptions.RegistroDuplicadoException;
import com.example.demo.model.Autor;
import com.example.demo.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("autores")
public class AutorController implements GenericController {

    private final AutorService autorService;

    @Qualifier("autorMapper")
    private final AutorMapper mapper;

    //posta um autor
    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid AutorDTO dto) {
        Autor autor = mapper.toEntity(dto);
        autorService.save(autor);
        URI location = gerarHeaderLocation(autor.getId());
        return ResponseEntity.created(location).build();

    }

    //obtem um autor por id
    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obeterDetalhes(@PathVariable("id") String id) {

        UUID uuid = UUID.fromString(id);
        return autorService.findById(uuid).map(Autor -> {
            AutorDTO dto = mapper.toDto(Autor);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //obtem todos autores por id
    @GetMapping("/all")
    public List<Autor> retornarAutores() {
        return autorService.listarTodos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") UUID id) {

        Optional<Autor> autorOptional = autorService.findById(id);
        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        autorService.excluirPorid(autorOptional.get());
        return ResponseEntity.noContent().build();
    }

    //obtem um autor por nome e nacionalidade (like)
    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisar(@RequestParam(value = "nome", required = false) String nome,
                                                    @RequestParam(value = "nacionalidade", required = false) String nacionalidade) {

        List<Autor> resultado = autorService.pesquisas(nome, nacionalidade);
        List<AutorDTO> lista = resultado.stream()
                .map(autor -> new AutorDTO(
                        autor.getId()
                        , autor.getNome()
                        , autor.getDataNascimento(),
                        autor.getNacionalidade()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") String id, @RequestBody @Valid AutorDTO dto) {


        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.findById(idAutor);
        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var autor = autorOptional.get();
        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());
        autor.setDataNascimento(dto.dataNascimento());
        autorService.atualizar(autor);
        return ResponseEntity.noContent().build();

    }

}

