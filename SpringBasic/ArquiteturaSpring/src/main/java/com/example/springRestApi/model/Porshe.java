package com.example.springRestApi.model;

import java.awt.*;

public class Porshe extends Carro {


    public Porshe(Motor motor) {
        super(motor);
        this.setModelo("GTS");
        this.setColor(Color.BLACK);
        this.setMontadora(Montadora.HONDA);
        this.setTipo(Tipo.CANIVETE);

    }


}
