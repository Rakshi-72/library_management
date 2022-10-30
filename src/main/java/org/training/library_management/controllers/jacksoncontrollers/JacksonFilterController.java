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
import org.springframework.web.bind.annotation.*;
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
     * @param librarianId The id of the librarian to be retrieved.
     * @return A ResponseEntity<MappingJacksonValue> object is being returned.
     */
    @GetMapping("/librarian/{librarianId}")
    @ApiOperation(value = "get librarian ", notes = "It takes a librarianId as a path variable, and returns a LibrarianDtoResponse object with ignored fields", response = LibrarianDtoResponse.class)
    public ResponseEntity<MappingJacksonValue> getLibrarianById(@PathVariable Integer librarianId) {

        return getMappingJacksonValueResponseEntity(librarianId, Set.of("name", "email", "booksBorrowed"));
    }

    /**
     * It takes a librarianId as a path variable, and returns a LibrarianDtoResponse object with ignored fields
     *
     * @param librarianId The id of the librarian to be retrieved.
     * @param fields      A set of fields that you want to be returned in the response.
     * @return A ResponseEntity<MappingJacksonValue> object.
     */
    @GetMapping("/librarian/params/{librarianId}")
    @ApiOperation(value = "get librarian ", notes = "It takes a librarianId as a path variable, and returns a LibrarianDtoResponse object with ignored fields", response = LibrarianDtoResponse.class)
    public ResponseEntity<MappingJacksonValue> getLibrarianByIdParams(@PathVariable Integer librarianId,
                                                                      @RequestParam Set<String> fields) {

        return getMappingJacksonValueResponseEntity(librarianId, fields);
    }

    /**
     * It takes in a librarianId and a set of fields, gets the librarian by id, creates a MappingJacksonValue object, adds
     * a filter to the MappingJacksonValue object, and returns a ResponseEntity with the MappingJacksonValue object
     *
     * @param librarianId The id of the librarian to be retrieved.
     * @param fields      The fields that we want to return in the response.
     * @return A ResponseEntity<MappingJacksonValue> object.
     */
    private ResponseEntity<MappingJacksonValue> getMappingJacksonValueResponseEntity(Integer librarianId, Set<String> fields) {
        LibrarianDtoResponse librarian = this.service.getLibrarianById(librarianId);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(librarian);
        FilterProvider provider = new SimpleFilterProvider()
                .addFilter("filter", SimpleBeanPropertyFilter.filterOutAllExcept(
                        fields));
        mappingJacksonValue.setFilters(provider);
        return new ResponseEntity<>(mappingJacksonValue, HttpStatus.OK);
    }
}
