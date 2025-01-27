package com.example.demo.service;


import com.example.demo.exceptions.OperacaoNaoPermitidaException;
import com.example.demo.model.Autor;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.LivroRepository;
import com.example.demo.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorValidator autorValidator;
    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;

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

    public void excluirPorid(Autor autor) {
        if(possuiLivro(autor)){
            throw new OperacaoNaoPermitidaException("Não é possivel excluir um autor possui livros cadastrados");
        }
        autorRepository.delete(autor);
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

    public boolean possuiLivro(Autor autor){
        return livroRepository.existsByAutor(autor);
    }

}
