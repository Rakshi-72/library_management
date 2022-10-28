package org.training.library_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

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
