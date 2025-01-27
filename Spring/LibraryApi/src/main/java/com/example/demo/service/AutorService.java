package com.example.demo.service;


import com.example.demo.model.Autor;
import com.example.demo.repository.AutorRepository;
import com.example.demo.validator.AutorValidator;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorValidator autorValidator;
    private final AutorRepository autorRepository;
    private final Validator validator;


    public AutorService(AutorValidator autorValidator, AutorRepository autorRepository, Validator validator) {
        this.autorValidator = autorValidator;
        this.autorRepository = autorRepository;
        this.validator = validator;
    }




    public Autor save(Autor autor) {
        autorValidator.validar(autor);
        return autorRepository.save(autor);
    }

    public void atualizar(Autor autor) {
        if(autor.getId() == null){
            throw new IllegalArgumentException("Esse id não existe no banco de dados.");
        }
        autorValidator.validar(autor);
        autorRepository.save(autor);
    }

    public Optional<Autor> findById(UUID id) {
        return autorRepository.findById(id);
    }

    public List<Autor> listarTodos() {
        return autorRepository.ListarTodosAutores();
    }

    public void excluir(String id) {
        UUID uuid = UUID.fromString(id);
        autorRepository.deleteById(uuid);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade) {
        if(nome != null && nacionalidade != null) {
            return autorRepository.findByNomeLikeAndNacionalidade("%" + nome + "%", "%" + nacionalidade + "%");
        }

        if (nome != null) {
            return autorRepository.findByNomeLike("%" +nome+ "%");
        }

        if (nacionalidade != null) {
            return autorRepository.findByNacionalidadeLike("%" +nacionalidade+ "%");
        }
        return autorRepository.findAll();
    }

}
