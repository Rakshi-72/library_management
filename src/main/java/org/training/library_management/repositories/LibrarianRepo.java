package org.training.library_management.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.library_management.model.Librarian;

public interface LibrarianRepo extends JpaRepository<Librarian, Integer> {
	Optional<Librarian> findByEmail(String email);
}
