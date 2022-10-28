package org.training.library_management.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private String email;
    private String password;
    @OneToMany(mappedBy = "librarian", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> booksBorrowed;


}
