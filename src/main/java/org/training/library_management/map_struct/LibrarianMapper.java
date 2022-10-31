package org.training.library_management.map_struct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.training.library_management.dtos.LibrarianDtoResponse;
import org.training.library_management.model.Librarian;

@Mapper(componentModel = "Spring")
public interface LibrarianMapper {

    LibrarianMapper INSTANCE = Mappers.getMapper(LibrarianMapper.class);

    LibrarianDtoResponse toDto(Librarian librarian);

    Librarian toLibrarian(LibrarianDtoResponse response);
}
