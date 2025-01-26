package com.example.demo.controller;

import com.example.demo.dto.AutorDTO;
import com.example.demo.model.Autor;
import com.example.demo.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("autores")
public class AutoController {

    private final AutorService autorService;

    public AutoController(AutorService autorService) {

        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody AutorDTO autor){

        Autor autorEntidade = autor.mapearParaAutor();
        autorService.save(autorEntidade);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autorEntidade.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obeterDetalhes(@PathVariable("id") String id){
        UUID uuid = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.findById(uuid);
        if(autorOptional.isPresent()){
            Autor autor = autorOptional.get();
            AutorDTO dto = new AutorDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/all")
    public List<Autor> retornarAutores(){
        return autorService.listarTodos();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> remover(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.findById(idAutor);
        if(autorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            autorService.excluir(String.valueOf(idAutor));
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisar(@RequestParam(value = "nome", required = false) String nome,
                                                    @RequestParam(value = "nacionalidade", required = false) String nacionalidade)
    {

        List<Autor> resultado = autorService.pesquisa(nome, nacionalidade);
        List<AutorDTO> lista = resultado.stream()
                .map(autor -> new AutorDTO(
                        autor.getId()
                        , autor.getNome()
                        , autor.getDataNascimento(),
                        autor.getNacionalidade()))
                        .collect(Collectors.toList());
                    return ResponseEntity.ok(lista);
    }
}
