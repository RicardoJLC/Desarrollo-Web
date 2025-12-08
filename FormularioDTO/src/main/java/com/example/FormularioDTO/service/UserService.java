package com.example.FormularioDTO.service;

import com.example.FormularioDTO.dto.CreateUserDTO;
import com.example.FormularioDTO.model.User;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final Map<Long, User> storage = new LinkedHashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    public UserService(){
        save(new CreateUserDTO("Carlos villlaba", 18, "Masculino"));
    }

    public User save(CreateUserDTO dto){
        Long id = idGen.getAndIncrement();
        User u = new User(id,dto.getName(), dto.getEdad(), dto.getGenero());
        storage.put(id, u);
        return u;
    }

    public Optional<User> findById(Long id){
        return Optional.ofNullable(storage.get(id));
    }

    public List<User> findAll(int page, int size, String search){
        List<User> all = new ArrayList<>(storage.values());
        if(search != null && !search.isBlank()){
            String s = search.toLowerCase();
            all = all.stream()
                    .filter(u -> u.getName().toLowerCase().contains(s) || u.getGenero().toLowerCase().contains(s) || u.getGenero().toLowerCase().contains(s))
                    .collect(Collectors.toList());
        }
        int from = page *  size;
        if(from >= all.size()) return Collections.emptyList();
        int to = Math.min(from + size, all.size());
        return all.subList(from, to);

    }


    public Optional<User> update(Long id, CreateUserDTO dto){
        User existing = storage.get(id);
        if(existing == null) return Optional.empty();
        existing.setName(dto.getName());
        existing.setEdad(dto.getEdad());
        existing.setGenero(dto.getGenero());
        return Optional.of(existing);
    }
}
