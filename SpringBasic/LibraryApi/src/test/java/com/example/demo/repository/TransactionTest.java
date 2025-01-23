package com.example.demo.repository;

import com.example.demo.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TransactionTest {

    @Autowired
    TransacaoService transacaoService;

    @Test
    public void test() {
        transacaoService.executar();
    }

    @Test
    public void transaTest(){
        transacaoService.atualizacaoSemSalvar();
    }

}
