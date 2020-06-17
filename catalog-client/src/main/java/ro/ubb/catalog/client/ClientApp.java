package ro.ubb.catalog.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;
import ro.ubb.catalog.client.Controller.ControllerRest;
import ro.ubb.catalog.core.model.Book;
import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.core.model.ValidatorException;
import ro.ubb.catalog.web.converter.BookConverter;
import ro.ubb.catalog.web.converter.ClientConverter;
import ro.ubb.catalog.web.dto.ClientDto;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Created by radu.
 */
public class ClientApp {
    public static final String URLBOOKS = "http://localhost:8080/api/books";
    public static final String URLCLIENTS = "http://localhost:8080/api/clients";

    public static void main(String[] args) throws RemoteException, FileNotFoundException, ValidatorException, SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "ro.ubb.catalog.client.config"
                );

        RestTemplate restTemplate = context.getBean(RestTemplate.class);




//        String url = "http://localhost:8080/api/students";


       ;

        ControllerRest controller = new ControllerRest(restTemplate,URLBOOKS,URLCLIENTS,new BookConverter(),new ClientConverter());
        UI ui = new UI(controller);

        ui.setBookId(ui.get_nr_of_books());
        ui.setClientId(ui.get_nr_of_clients());
        ui.main();

       /*String dawd = URLBOOKS + "/add";
       ClientsDto c = restTemplate.getForObject("http://localhost:8080/api/clients/get/",ClientsDto.class);
       ClientDto lawl = restTemplate.postForObject("http://localhost:8080/api/clients/saveClient",new ClientDto(new Long(100),"dawd"),ClientDto.class);
       try {
           ClientDto cl2  = new ClientDto();
           restTemplate.postForObject("http://localhost:8080/api/clients/save",cl2,ClientDto.class);
           ClientDto cl = restTemplate.postForObject("http://localhost:8080/api/clients/save", cl2, ClientDto.class);

       }catch( org.springframework.web.client.HttpServerErrorException error){
           System.out.println(error.getCause());

           }


        BookDto b = new BookDto();
       restTemplate.postForObject(
               "http://localhost:8080/api/books/save" ,
               b,
               BookDto.class
       );
        BookDto savedBook = restTemplate.postForObject(
                URLBOOKS ,
                b,
                BookDto.class
        );



        /*StudentDto savedStudent = restTemplate.postForObject(
                URL,
                new ClientDto(new Long(1),"sn1"),
                StudentDto.class);
        System.out.println("savedStudent: " + savedStudent);

        System.out.println("update:");
        savedStudent.setName("updated");
        restTemplate.put(URL + "/{id}", savedStudent, savedStudent.getId());
        printAllStudents(restTemplate);

        System.out.println("delete: ");
        restTemplate.delete(URL + "/{id}", savedStudent.getId());
        printAllStudents(restTemplate);
*/
        System.out.println("bye ");
    }

    private static void printAllStudents(RestTemplate restTemplate) {
        //StudentsDto allStudents = restTemplate.getForObject(URL, StudentsDto.class);
        //System.out.println(allStudents);
    }
}
