package org.training.library_management.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.training.library_management.exceptions.LibrarianNotFoundException;
import org.training.library_management.model.Librarian;
import org.training.library_management.repositories.LibrarianRepo;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private LibrarianRepo repo;

    /**
     * It takes a username, finds the corresponding Librarian object in the database, and returns a CustomUserDetails object
     * that contains the Librarian object
     *
     * @param username The username of the user to load.
     * @return A CustomUserDetails object.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Librarian librarian = repo.findByEmail(username).orElseThrow(() -> new LibrarianNotFoundException(username));
        return new CustomUserDetails(librarian);
    }

}
