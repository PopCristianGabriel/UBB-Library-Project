package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.Book;
import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.core.model.ValidatorException;
import ro.ubb.catalog.core.repository.Sorting.Sort;
import ro.ubb.catalog.core.repository.Sorting.SortingRepository;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
public class BookServiceImpl implements BookService{

    @Autowired
    SortingRepository<Long, Book> books;

    @Override
    public List<Book> getAllBooks() throws FileNotFoundException, ValidatorException, ParserConfigurationException {
        return (List<Book>) books.findAll();
    }

    @Override
    public Book saveBook(Book book) throws ParserConfigurationException, ValidatorException, IOException {
        this.books.save(book);
        return book;
    }

    @Override
    public Book updateBook(Book book) throws ParserConfigurationException, ValidatorException, IOException {
        this.books.update(book);
        return book;
    }

    @Override
    public void deleteBook(Long id) throws IOException, ParserConfigurationException {
        this.books.delete(id);
    }

    @Override
    public Book get_entity_by_id(Long id) {
        return (Book) this.books.get_entity_by_id(id);
    }

    @Override
    public void add_statistics(String key, Integer value) {
        this.books.add_statistic(key,value);
    }

    @Override
    public void add_statistics(String key) {
        this.books.add_statistic(key);
    }

    @Override
    public String get_most() {
        return this.books.get_most();
    }

    @Override
    public ArrayList<Book> get_books_sorted(Sort sort) {
        return (ArrayList<Book>) this.books.findAll(sort);
    }

    @Override
    public Boolean find_one(Long id) throws ValidatorException {
        return this.books.findOne(id) != null;
    }

    @Override
    public void truncate_tables() throws SQLException {
        this.books.truncateTables();
    }

    @Override
    public void commit() {
        this.books.commit();
    }

    @Override
    public void flush() {
    this.books.flush();
    }

    @Override
    public void write_to_data_base() throws SQLException {
        this.books.writeToDataBase();
    }
}
