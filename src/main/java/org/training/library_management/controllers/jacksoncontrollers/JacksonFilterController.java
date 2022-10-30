package org.training.library_management.controllers.jacksoncontrollers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.library_management.dtos.LibrarianDtoResponse;
import org.training.library_management.services.LibrarianService;

import java.util.Set;

@RestController
@RequestMapping("/api/jackson")
@Validated
public class JacksonFilterController {
    @Autowired
    private LibrarianService service;

    /**
     * It takes a librarianId as a path variable, and returns a LibrarianDtoResponse object with ignored fields
     *
     * @param librarianId The id of the librarian to be fetched.
     * @return A ResponseEntity object with a MappingJacksonValue object as its body.
     */
    @GetMapping("/librarian/{librarianId}")
    @ApiOperation(value = "get librarian ", notes = "It takes a librarianId as a path variable, and returns a LibrarianDtoResponse object with ignored fields", response = LibrarianDtoResponse.class)
    public ResponseEntity<MappingJacksonValue> getLibrarianById(@PathVariable Integer librarianId) {

        LibrarianDtoResponse librarian = this.service.getLibrarianById(librarianId);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(librarian);
        FilterProvider provider = new SimpleFilterProvider()
                .addFilter("filter", SimpleBeanPropertyFilter.filterOutAllExcept(
                        Set.of("name", "age", "gender", "email", "booksBorrowed")));
        mappingJacksonValue.setFilters(provider);
        return new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }
}
