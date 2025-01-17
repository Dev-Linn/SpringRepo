package com.example.springRestApi.model;

import java.awt.*;

public class Porshe extends Carro {


    public Porshe(String modelo, Color color, Motor motor, Montadora montadora) {
        super(motor);
        this.setModelo(modelo);
        this.setColor(Color.BLACK);
        this.setMontadora(Montadora.HONDA);
    }
}
