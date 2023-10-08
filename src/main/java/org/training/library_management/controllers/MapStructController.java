package org.training.library_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.library_management.dtos.LibrarianDtoResponse;
import org.training.library_management.map_struct.LibrarianMapper;
import org.training.library_management.repositories.LibrarianRepo;

@RestController
@RequestMapping("/api/mapstruct")
public class MapStructController {

    @Autowired
    private LibrarianRepo repo;

    @Autowired
    private LibrarianMapper mapper;

    @GetMapping("/user/{id}")
    public LibrarianDtoResponse getLibrarianDtoResponse(@PathVariable Integer id) {
        return mapper.toDto(repo.findById(id).get());
    }
}
