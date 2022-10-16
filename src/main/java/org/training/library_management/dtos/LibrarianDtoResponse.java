package org.training.library_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibrarianDtoResponse {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private String email;
    // private Set<BookDto> booksBorrowed;
}
