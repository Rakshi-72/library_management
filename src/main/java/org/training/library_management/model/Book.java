package org.training.library_management.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String author;
    private Integer price;
    private Integer noOfPages;
    private Integer publishedYear;
    @ManyToOne(fetch = FetchType.LAZY)

    @JsonIgnore
    private Librarian librarian;
    private LocalTime date_of_borrowed;

    public void setDate_of_borrowed(LocalDate now) {
    }

}
