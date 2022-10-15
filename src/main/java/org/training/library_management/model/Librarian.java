package org.training.library_management.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Librarian {
    @Id
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private String email;
    private String password;
    private List<Book> booksBarrowed = new ArrayList<>();
}
