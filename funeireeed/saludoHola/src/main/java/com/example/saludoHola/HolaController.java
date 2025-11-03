package com.example.saludoHola;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController {

    @GetMapping("/saludo")

    public String saludo(){
        String nombre = "Ricardo";
        int edad = 19;

        return "<Strong> Saludo Personalizado </Strong>" +
                "Hola, " + nombre + " tienes " + edad + " años." + "\n" ;



    }
}
