package com.example.FormularioDTO.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUserDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String name;

    @Min(value = 1, message = "La edad debe ser mayor que 0")
    private int edad;

    @NotBlank(message = "El género es obligatorio")
    private String genero;

    public CreateUserDTO() {}

    public CreateUserDTO(String name, int edad, String genero) {
        this.name = name;
        this.edad = edad;
        this.genero = genero;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}
