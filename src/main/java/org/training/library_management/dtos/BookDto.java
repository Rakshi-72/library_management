package org.training.library_management.dtos;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.training.library_management.config.Views;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto extends RepresentationModel<BookDto> {
    private Integer id;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 3, max = 30, message = "book name should be within the range of 3 to 20 characters")
    @JsonView(Views.External.class)
    private String name;

    @NotEmpty(message = "author name should not be empty")
    @JsonView(Views.External.class)
    private String author;
    @JsonView(Views.External.class)
    private Integer price;
    @JsonView(Views.External.class)
    private Integer noOfPages;
    @JsonView(Views.External.class)
    private Integer publishedYear;

    @Override
    public BookDto add(Link... links) {
        return super.add(links);
    }
}
