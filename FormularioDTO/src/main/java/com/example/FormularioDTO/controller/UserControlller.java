package com.example.FormularioDTO.controller;

import com.example.FormularioDTO.dto.CreateUserDTO;
import com.example.FormularioDTO.model.User;
import com.example.FormularioDTO.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(u -> ResponseEntity.ok((Object) u))
                .orElse(
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(Map.of("message", "Usuario no encontrado"))
                );
    }

    @GetMapping
    public List<User> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search
    ) {
        return service.findAll(page, size, search);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid CreateUserDTO dto) {
        User created = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid CreateUserDTO dto) {
        return service.update(id, dto)
                .map(u -> ResponseEntity.ok((Object) u))
                .orElse(
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(Map.of("message", "Usuario no encontrado"))
                );
    }
}
