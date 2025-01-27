package com.example.demo.validator;

import com.example.demo.exceptions.RegistroDuplicadoException;
import com.example.demo.model.Autor;
import com.example.demo.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {


    private AutorRepository autorRepository;

    public AutorValidator(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public void validar(Autor autor){
        if(existeAutor(autor)){
            throw new RegistroDuplicadoException("Autor já cadastrado");
        }
    }

    private boolean existeAutor(Autor autor){
        Optional<Autor> autorEncontrado = autorRepository.findByNome(autor.getNome());
        if(autorEncontrado.isEmpty()){
            return false;
        }

        return autor.getId() == null || !autor.getId().equals(autorEncontrado.get().getId());

    }
}
