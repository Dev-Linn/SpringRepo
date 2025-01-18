package com.example.springRestApi.model.configuration;


import com.example.springRestApi.model.Motor;
import com.example.springRestApi.model.TipoMotor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MontadoraConfiguration {

    @Bean(name = "motorAspirado")
    public Motor motorAspirado(){
        var motor = new Motor();
        motor.setCavalos(600);
        motor.setCilindros(4);
        motor.setModel("GTS");
        motor.setLitragem(3.0);
        motor.setTipo(TipoMotor.ASPIRADO);

        return motor;
    }

    @Bean(name = "motorEletrico")
    public Motor motorEletrico(){
        var motor = new Motor();
        motor.setCavalos(1200);
        motor.setCilindros(6);
        motor.setModel("ELON MUSK");
        motor.setLitragem(6.0);
        motor.setTipo(TipoMotor.ELETRICO);
        return motor;
    }

    @Bean(name = "motorTurbo")
    public Motor motorTurbo(){
        var motor = new Motor();
        motor.setCavalos(1800);
        motor.setCilindros(12);
        motor.setModel("TORETTO");
        motor.setLitragem(8.0);
        motor.setTipo(TipoMotor.TURBO);
        return motor;
    }

}
