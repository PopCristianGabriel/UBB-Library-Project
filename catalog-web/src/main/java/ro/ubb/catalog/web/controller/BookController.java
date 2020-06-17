package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import ro.ubb.catalog.core.model.Book;
import ro.ubb.catalog.core.model.ClientStatisticsModel;
import ro.ubb.catalog.core.model.ListObject;
import ro.ubb.catalog.core.model.ValidatorException;
import ro.ubb.catalog.core.repository.Sorting.Sort;
import ro.ubb.catalog.core.service.BookService;
import ro.ubb.catalog.web.converter.BookConverter;
import ro.ubb.catalog.web.dto.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by radu.
 */

@RestController
@RequestMapping("/books")
public class BookController {
    public static final Logger log= LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;
    @Autowired
    private BookConverter bookConverter;


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    BooksDto getBooks() throws FileNotFoundException, ValidatorException, ParserConfigurationException {
        //todo: log
        this.log.info("method get books was called");
        return new BooksDto(bookConverter
                .convertModelsToDtos(this.bookService.getAllBooks()));

    }

    @RequestMapping(value = "/dawd", method = RequestMethod.GET)
    BookDto dawd() throws FileNotFoundException, ValidatorException, ParserConfigurationException {
        //todo: log
        this.log.info("method dawd was called");
        return new BookDto(new Long(1),"a","a",20,20);

    }

    @RequestMapping(value = "/truncate", method = RequestMethod.POST)
    void truncate_tables() throws SQLException {
        this.log.info("method truncate tables was called");
        this.bookService.truncate_tables();
    }

    @RequestMapping(value = "/commit", method = RequestMethod.POST)
    void commit(){
        this.log.info("method commit was called");
        this.bookService.commit();
    }
    @RequestMapping(value = "/flush", method = RequestMethod.POST)
    void flush(){
        this.log.info("method flush was called");
        this.bookService.flush();
    }

    @RequestMapping(value = "/writeToDataBase", method = RequestMethod.POST)
    void write_to_data_base() throws SQLException {
        this.log.info("method books write to data base was called");
        this.bookService.write_to_data_base();
    }
    @RequestMapping(value = "/writeToDataBase2", method = RequestMethod.POST)
    Book write_to_data_base2(@RequestBody Book IDK) throws SQLException {
        this.log.info("method books write to data base was called");
        this.bookService.write_to_data_base();
        return new Book(new Long(1),"dawd","dwad",1,1);
    }

    @RequestMapping(value = "/getBiggestId",method = RequestMethod.GET)
    Book get_biggest_id() throws FileNotFoundException, ValidatorException, ParserConfigurationException {
        Book biggest = new Book((long) 1,"dawd","dawd",2,2);
        for(Book book : this.bookService.getAllBooks()){
            if(book.getid().longValue() > biggest.getId().longValue()){
                biggest = book;
            }
        }
        return biggest;
    }

    @RequestMapping(value = "/saveBook2", method = RequestMethod.POST)
    void saveBook2(@RequestBody Book book) throws IOException, ValidatorException, ParserConfigurationException {
        //todo log
        System.out.println("dawd");
        this.log.info("Method save book was called");
         this.bookService.saveBook(book);

    }

    @RequestMapping(value = "/saveBook", method = RequestMethod.POST)
    BookDto saveBook(@RequestBody BookDto bookDto) throws IOException, ValidatorException, ParserConfigurationException {
        //todo log
        System.out.println("dawd");
        this.log.info("Method save book was called");
            this.bookService.saveBook(this.bookConverter.convertDtoToModel(bookDto));
            return bookDto;

    }


    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    BookDto updateBook(@RequestBody BookDto bookDto) throws ParserConfigurationException, ValidatorException, IOException {
        System.out.println("dawd");
        //todo: log
        this.log.info("Method update book was called");
        this.bookService.updateBook(this.bookConverter.convertDtoToModel(bookDto));
        return bookDto;
    }

    @RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
    Book deleteBook(@RequestBody Long id) throws IOException, ParserConfigurationException {
        //todo:log
        System.out.println("dawd");
        this.log.info("Method delete book was called");
        Book b = this.bookService.get_entity_by_id(id);
        this.bookService.deleteBook(id);
        return b;


    }

    @RequestMapping(value ="saveManyBooks",method = RequestMethod.POST)
    Book add_many_books(@RequestBody List<Book> books) throws IOException, ValidatorException, ParserConfigurationException {
        for(Book book : books){
            this.bookService.saveBook(book);
        }
        return new Book(new Long(1),"dawd","dwad",2,2);
    }

    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    Book get_entity_by_id(@RequestBody Long id){
        this.log.info("Method get book by id was called");
        return this.bookService.get_entity_by_id(id);
    }

    @RequestMapping(value = "/sorted", method = RequestMethod.POST)
    ListObject get_books_sorted(@RequestBody Sort sort){
        this.log.info("Method get books sorted was called");
        return new ListObject(this.bookService.get_books_sorted(sort));
    }

    @RequestMapping(value = "/addStatistics1Param", method = RequestMethod.POST)
    void get_books_sorted(@RequestBody String key){
        this.log.info("Method add statistics 1 parameter was called");
        this.bookService.add_statistics(key);
    }

    @RequestMapping(value = "/addStatistics2Param", method = RequestMethod.POST)
    void get_books_sorted(@RequestBody ClientStatisticsModel model){
        this.log.info("Method add statistics 2 parameters was called");
        this.bookService.add_statistics(model.get_name(),model.value());
    }
    @RequestMapping(value = "/getMost", method = RequestMethod.POST)
    String get_entity_by_id(){
        this.log.info("Method get most rented  book was called");
        return this.bookService.get_most();
    }

    @RequestMapping(value = "/findOne", method = RequestMethod.POST)
    Boolean find_one(@RequestBody Long id) throws ValidatorException {
        this.log.info("Method book find one was called");
        return this.bookService.find_one(id);
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    ListObject get_books_sorted(@RequestParam int id, @RequestParam int name, @RequestParam int title, @RequestParam int year,
                                @RequestParam int price, @RequestParam int directionInt   ){

        this.log.info("Method get book sorted from site was called");
        String idS="",autorNameS="",tileS="",yearS="",priceS="";



        if(id==1){
            idS="id";
        }



        if(name==1){
            autorNameS="autorName";
        }


        if(title==1){
            tileS="title";
        }


        if(year==1){
            yearS="year";
        }


        if(price==1){
            priceS="price";
        }




        Sort sort;
        if(directionInt==0){
            sort=new Sort(autorNameS,tileS,yearS,priceS,idS);
        }
        else{
            sort=new Sort(1,autorNameS,tileS,yearS,priceS,idS);
        }

        return new ListObject<Book>((this.bookService.get_books_sorted(sort)));
    }


}
