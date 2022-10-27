package org.training.library_management.dtos;

import org.springframework.http.HttpHeaders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiWithHeaderResponse<T> {
    T element;
    HttpHeaders headers;
}
