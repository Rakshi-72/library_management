package org.training.library_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDtoSimple {
    private Integer id;
    private String name;
    private String author;
    private Integer price;
    private Integer noOfPages;
    private Integer publishedYear;
}
