package org.training.library_management.services.implement;

import java.util.List;

import org.training.library_management.dtos.BookDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private List<BookDto> books;
    private int number;
    private int size;
    private int numberOfElements;
    private int totalPages;
    private boolean last;

}
