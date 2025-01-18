package com.example.springRestApi.model.montadora;

import java.awt.*;

public class Carro {
    private String modelo;
    private Color color;
    private Motor motor;
    private Montadora montadora;
    private Tipo tipo;

   public Carro(Motor motor) {
       this.motor = motor;
   }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Montadora getMontadora() {
        return montadora;
    }

    public void setMontadora(Montadora montadora) {
        this.montadora = montadora;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public CarroStatus darIgnicao(Chave chave) {
       if (chave.getMontadora()!= this.montadora){
           return new CarroStatus("Não é possivel inciar o carro com essa chave");
       } else if (chave.getTipo()!= this.tipo) {
           return new CarroStatus("O tipo da chave não é compativel");
       }


        return new CarroStatus("Carro ligado rodando com o motor: " + motor);
    }
}
