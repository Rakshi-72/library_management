package org.training.library_management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.library_management.model.Book;
import org.training.library_management.model.Librarian;

public interface BookRepo extends JpaRepository<Book, Integer> {
    /**
     * Find all books whose name contains the search_key.
     * 
     * @param search_key The search key that you want to search for.
     * @return A list of books that contain the search_key in their name.
     */
    List<Book> findByNameContaining(String search_key);

    List<Book> findByLibrarian(Librarian librarian);
}
