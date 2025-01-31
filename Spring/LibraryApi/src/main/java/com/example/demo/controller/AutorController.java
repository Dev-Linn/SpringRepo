package com.example.demo.controller;

import com.example.demo.dto.AutorDTO;
import com.example.demo.dto.ErroResposta;
import com.example.demo.exceptions.OperacaoNaoPermitidaException;
import com.example.demo.exceptions.RegistroDuplicadoException;
import com.example.demo.model.Autor;
import com.example.demo.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
public class AutorController {

    private final AutorService autorService;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid AutorDTO autor){

        try {
            Autor autorEntidade = autor.mapearParaAutor();
            autorService.save(autorEntidade);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autorEntidade.getId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (RegistroDuplicadoException e) {
            var erroDto = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable("id") UUID id) {
        try {
            Optional<Autor> autorOptional = autorService.findById(id);
            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            autorService.excluirPorid(autorOptional.get());
            return ResponseEntity.noContent().build();
        }catch (OperacaoNaoPermitidaException e){
            var erroResposta = ErroResposta.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroResposta.status()).body(erroResposta);
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

    @PutMapping("{id}")
    public ResponseEntity<Object>atualizar(@PathVariable("id")String id, @RequestBody AutorDTO dto){

        try {
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
        } catch (RegistroDuplicadoException e) {
            var erroDto = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);

        }

    }
}
