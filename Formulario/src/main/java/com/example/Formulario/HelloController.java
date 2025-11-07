package com.example.Formulario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @PostMapping("/saludo")
    public String saludo(
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String correo,
            @RequestParam String contrasena
    ) {
        return "<h1>Hola " + nombre + " " + apellido + "</h1>"
                + "<p>Tu correo es: " + correo + "</p>"
                + "<p>Tu contraseña (no la muestres en producción): " + contrasena + "</p>";
    }
}

