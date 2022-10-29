package org.training.library_management.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
public class BookDto extends RepresentationModel<BookDto> {
    private Integer id;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 3, max = 30, message = "book name should be within the range of 3 to 20 characters")
    private String name;

    @NotEmpty(message = "author name should not be empty")
    private String author;
    private Integer price;
    private Integer noOfPages;
    private Integer publishedYear;

    @Override
    public BookDto add(Link... links) {
        return super.add(links);
    }
}
