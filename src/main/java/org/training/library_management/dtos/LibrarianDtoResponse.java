package org.training.library_management.dtos;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.training.library_management.config.Views;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonFilter("filter") - for Mapping jackson values
public class LibrarianDtoResponse extends RepresentationModel<LibrarianDtoResponse> {
    @JsonView(Views.Internal.class)
    private Integer id;
    @JsonView(Views.External.class)
    private String name;

    @JsonView(Views.Internal.class)
    private Integer age;

    @JsonView(Views.External.class)
    private String gender;

    @JsonView(Views.External.class)
    private String email;

    @JsonView(Views.Internal.class)
    private List<BookDto> booksBorrowed;

    @Override
    public LibrarianDtoResponse add(Link... links) {
        return super.add(links);
    }
}
