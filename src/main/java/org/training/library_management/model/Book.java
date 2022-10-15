package org.training.library_management.model;

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
public class Book {
    @Id
    private Integer id;
    private String name;
    private String author;
    private Integer price;
    private Integer noOfPages;
    private Integer publishedYear;
}
