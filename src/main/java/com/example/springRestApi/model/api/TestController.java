package com.example.springRestApi.model.api;


import com.example.springRestApi.model.Chave;
import com.example.springRestApi.model.Motor;
import com.example.springRestApi.model.Porshe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carros")
public class TestController {

    @Autowired
    private Motor motor;

    @PostMapping()
    public CarroStatus ligarCarro(@RequestBody Chave chave) {

        var carro = new Porshe(motor);
        return carro.darIgnicao(chave);
    }
}
