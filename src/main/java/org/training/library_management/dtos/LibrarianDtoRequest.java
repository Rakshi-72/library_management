package org.training.library_management.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibrarianDtoRequest {
    private Integer id;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 3, max = 20, message = "name should be within 3 to 20 characters")
    private String name;
    private Integer age;
    private String gender;
    @Email(message = "please enter a valid email")
    @Size(min = 8, max = 40, message = "email should be between 8-25 characters")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "password must contain 1 capital, 1 small, 1 numeric and one special character")
    private String password;

}
