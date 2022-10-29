package org.training.library_management.dtos;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibrarianDtoResponse extends RepresentationModel<LibrarianDtoResponse> {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private String email;
    private List<BookDto> booksBorrowed;

    @Override
    public LibrarianDtoResponse add(Link... links) {
        return super.add(links);
    }
}
