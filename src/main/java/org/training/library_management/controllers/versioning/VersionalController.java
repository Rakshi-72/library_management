package org.training.library_management.controllers.versioning;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.library_management.config.Views;
import org.training.library_management.dtos.LibrarianDtoResponse;
import org.training.library_management.services.LibrarianService;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/versioning")
public class VersionalController {

    @Autowired
    private LibrarianService service;

    @JsonView(Views.Internal.class)
    @GetMapping(value = "/all", params = "version=v1", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get all librarians ", notes = "It returns a list of all librarians in the database", response = LibrarianDtoResponse.class)
    public ResponseEntity<List<LibrarianDtoResponse>> getAllLibrarians() {
        return new ResponseEntity<>(service.getAllLibrarians(), HttpStatus.OK);
    }

    @JsonView(Views.External.class)
    @GetMapping(value = "/all", params = "version=v2")
    @ApiOperation(value = "get all librarians ", notes = "It returns a list of all librarians in the database", response = LibrarianDtoResponse.class)
    public ResponseEntity<List<LibrarianDtoResponse>> getAllLibrarians2() {
        return new ResponseEntity<>(service.getAllLibrarians(), HttpStatus.OK);
    }
}
