package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Book;
import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.core.model.ValidatorException;
import ro.ubb.catalog.core.repository.Sorting.Sort;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks() throws FileNotFoundException, ValidatorException, ParserConfigurationException;

    Book saveBook(Book book) throws ParserConfigurationException, ValidatorException, IOException;

    Book updateBook( Book book) throws ParserConfigurationException, ValidatorException, IOException;

    void deleteBook(Long id) throws IOException, ParserConfigurationException;

    Book get_entity_by_id(Long id);

    void add_statistics(String key, Integer value);
    void add_statistics(String key);
    String get_most();

    ArrayList<Book> get_books_sorted(Sort sort);

    Boolean find_one(Long id) throws ValidatorException;

    void truncate_tables() throws SQLException;
    void commit();
    void flush();
    void write_to_data_base() throws SQLException;
}
