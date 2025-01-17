package com.example.springRestApi.model.configuration;


import com.example.springRestApi.model.Motor;
import com.example.springRestApi.model.TipoMotor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MontadoraConfiguration {

    @Bean
    public Motor motor(){
        var motor = new Motor();
        motor.setCavalos(600);
        motor.setCilindros(4);
        motor.setModel("GTS");
        motor.setLitragem(3.0);
        motor.setTipo(TipoMotor.ASPIRADO);
        return motor;
    }

}
