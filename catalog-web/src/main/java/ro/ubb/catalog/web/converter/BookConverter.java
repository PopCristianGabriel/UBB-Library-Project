package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Book;
import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.web.dto.BookDto;
import ro.ubb.catalog.web.dto.ClientDto;
@Component
public class BookConverter extends BaseConverter<Book, BookDto> {
    @Override
    public Book convertDtoToModel(BookDto dto) {
        Book book = Book.builder().autorName(dto.getAutorName())
                .title(dto.getTitle())
                .price(dto.getPrice())
                .year(dto.getYear()).build();
        book.setId(dto.getId());
        return book;
    }

    @Override
    public BookDto convertModelToDto(Book book) {
        BookDto bookDto = BookDto.builder().year(book.getYear())
                .autorName(book.getAutorName())
                .price(book.getPrice())
                .title(book.getTitle()).build();
        bookDto.setId(book.getId());
        return bookDto;
    }
}
