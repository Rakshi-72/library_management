package org.training.library_management.dtos;

import java.util.ArrayList;
import java.util.List;

import org.training.library_management.model.Book;

public class LibrarianDtoResponse {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private String email;
    private List<Book> booksBarrowed = new ArrayList<>();
}
