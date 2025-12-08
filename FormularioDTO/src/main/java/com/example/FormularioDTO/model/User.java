package com.example.FormularioDTO.model;

public class User {

    private Long id;
    private String name;
    private int edad;
    private String genero;

    public User() {}

    public User(Long id, String name, int edad, String genero) {
        this.id = id;
        this.name = name;
        this.edad = edad;
        this.genero = genero;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
}
