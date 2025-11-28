package com.example.List.Controller;

import com.example.Explicacion.dto.CreateUserDTO;
import com.example.Explicacion.model.User;
import com.example.Explicacion.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // 1) Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    // 2) Listar con query params usando findAll
    @GetMapping
    public List<User> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search
    ) {
        return service.findAll(page, size, search);
    }

    // 3) Crear usuario
    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid CreateUserDTO dto) {
        User created = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // 4) Actualizar usuario (AGREGADO DESDE LA IMAGEN)
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid CreateUserDTO dto) {
        return service.update(id, dto)
                .map(u -> ResponseEntity.ok((Object) u)) // Devuelve User si existe
                .orElse(
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(Map.of("error", "Usuario no encontrado")) // Devuelve JSON si no existe
                );
    }
}