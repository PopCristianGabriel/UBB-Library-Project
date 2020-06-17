package ro.ubb.catalog.web.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BooksDto {
    private Set<BookDto> books;
}
