package org.training.library_management.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
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
    private Set<Book> booksBorrowed;
}
