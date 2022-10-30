package org.training.library_management.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.library_management.config.Views;
import org.training.library_management.dtos.LibrarianDtoResponse;
import org.training.library_management.services.LibrarianService;

@RestController
@RequestMapping("/api/jsonview/librarians")
public class JsonViewController {
    @Autowired
    private LibrarianService service;

    @GetMapping("/internal/{librarianId}")
    @JsonView(Views.Internal.class)
    @ApiOperation(value = "get librarian ", notes = "It takes a librarianId as a path variable, and returns a LibrarianDtoResponse object", response = LibrarianDtoResponse.class)
    public ResponseEntity<LibrarianDtoResponse> getLibrarianByIdInternal(@PathVariable Integer librarianId) {
        return new ResponseEntity<>(this.service.getLibrarianById(librarianId), HttpStatus.OK);
    }

    @GetMapping("/external/{librarianId}")
    @JsonView(Views.External.class)
    @ApiOperation(value = "get librarian ", notes = "It takes a librarianId as a path variable, and returns a LibrarianDtoResponse object", response = LibrarianDtoResponse.class)
    public ResponseEntity<LibrarianDtoResponse> getLibrarianByIdExternal(@PathVariable Integer librarianId) {
        return new ResponseEntity<>(this.service.getLibrarianById(librarianId), HttpStatus.OK);
    }
}
