package com.example.demo.service;


import com.example.demo.model.Autor;
import com.example.demo.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {


    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor save(Autor autor) {
        return autorRepository.save(autor);
    }

    public void atualizar(Autor autor) {
        if(autor.getId() == null){
            throw new IllegalArgumentException("Esse id não existe no banco de dados.");
        }
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
