package org.training.library_management.controllers.hateos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.library_management.dtos.LibrarianDtoResponse;
import org.training.library_management.repositories.LibrarianRepo;
import org.training.library_management.services.LibrarianService;

@RestController
@RequestMapping("/api/hateos")
@Validated
public class HATEOSController {

    @Autowired
    private LibrarianRepo repo;
    @Autowired
    private LibrarianService service;

    @GetMapping("/librarians")
    public List<LibrarianDtoResponse> getAllUsers() {
        return service.getAllLibrarians();
    }

}
