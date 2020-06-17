package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.bind.annotation.*;

import ro.ubb.catalog.core.model.*;
import ro.ubb.catalog.core.repository.Sorting.Sort;
import ro.ubb.catalog.core.service.ClientService;
import ro.ubb.catalog.web.converter.ClientConverter;
import ro.ubb.catalog.web.dto.ClientDto;
import ro.ubb.catalog.web.dto.ClientsDto;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by radu.
 */

@RestController
@RequestMapping("/clients")
public class ClientController {
    public static final Logger log= LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientConverter clientConverter;




    @RequestMapping(value = "/get", method = RequestMethod.GET)
    ClientsDto getClients() throws FileNotFoundException, ValidatorException, ParserConfigurationException {
        //todo: log

        log.info("Method get clients was called");
        return new ClientsDto(clientConverter
                .convertModelsToDtos(this.clientService.getAllClients()));
    }
    @RequestMapping(value = "/writeToDataBase", method = RequestMethod.POST)
    void write_to_data_base() throws SQLException {
        log.info("Method write to data base was called");
        this.clientService.write_to_data_base();
    }
    @RequestMapping(value = "/writeToDataBase2", method = RequestMethod.POST)
    Client write_to_data_base2(@RequestBody Client IDC) throws SQLException {
        log.info("Method write to data base was called");
        this.clientService.write_to_data_base();
        return new Client(new Long(1),"dawd");
    }

    @RequestMapping(value = "/commit", method = RequestMethod.POST)
    void commit(){
        log.info("Method commit was called");
        this.clientService.commit();
    }
    @RequestMapping(value = "/flush", method = RequestMethod.POST)
    void flush(){
        log.info("Method flush was called");
        this.clientService.flush();
    }

    @RequestMapping(value = "saveClient2",method = RequestMethod.POST)
    Client save_client(@RequestBody Client client) throws IOException, ValidatorException, ParserConfigurationException {
        log.info("Method save client was called");
        this.clientService.saveClient(client);
        return client;


    }

    @RequestMapping(value = "saveClient",method = RequestMethod.POST)
    ClientDto lawll(@RequestBody ClientDto client) throws IOException, ValidatorException, ParserConfigurationException {
        log.info("Method save client was called");
        this.clientService.saveClient(clientConverter.convertDtoToModel(client));
        return client;


    }

    @RequestMapping(value = "/sortedClients", method = RequestMethod.POST)
    ClientsDto get_clients_sorted(@RequestBody Sort sort){
        log.info("Method sort clients was called");
        return new ClientsDto(this.clientConverter.convertModelsToDtos(this.clientService.get_clients_sorted(sort)));
    }




    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    ListObject<Client> get_clients_sorted_web(@RequestParam int id,@RequestParam int name, @RequestParam int moneySpent, @RequestParam int directionInt){
        log.info("Method sort clients was called");
        String idS="",nameS="",moneyS="";
        if(id==1){
            idS="id";
        }



        if(name==1){
            nameS="name";
        }



        if(moneySpent==1){
            moneyS="moneySpent";
        }



        Sort sort;
        if(directionInt==0){
            sort=new Sort(idS,nameS,moneyS);
        }
        else{
            sort=new Sort(1,idS,nameS,moneyS);
        }

        return new ListObject<Client>((this.clientService.get_clients_sorted(sort)));
    }




    @RequestMapping(value = "/updateClient", method = RequestMethod.POST)
    ClientDto updateClient(@RequestBody ClientDto clientDto) throws ParserConfigurationException, ValidatorException, IOException {
        System.out.println("dawd");
        log.info("Method updat client was called");
        this.clientService.updateClient(this.clientConverter.convertDtoToModel(clientDto));
        return clientDto;
    }
    @RequestMapping(value = "/deleteClient", method = RequestMethod.POST)
    Client deleteClient(@RequestBody Long id) throws IOException, ParserConfigurationException {
        log.info("Method delete client was called");
        System.out.println("dawd");
        Client client = this.clientService.get_entity_by_id(id);
        this.clientService.deleteClient(id);
        return client;


    }


    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    Client get_entity_by_id(@RequestBody Long id){

        log.info("Method get client by id was called");
        return this.clientService.get_entity_by_id(id);
    }

    @RequestMapping(value = "/getMost", method = RequestMethod.POST)
    String get_entity_by_id(){
        log.info("Method get most active client was called");
        return this.clientService.get_most();
    }


    @RequestMapping(value = "/findOne", method = RequestMethod.POST)
    Boolean find_one(@RequestBody Long id) throws ValidatorException {
        log.info("Method find one client was called");
        return this.clientService.find_one(id);
    }

    @RequestMapping(value = "/addBookToClient", method = RequestMethod.POST)
    void addBookToClient(@RequestBody BuyBookBody body){
        log.info("Method add book to client was called");
        Client client = this.clientService.get_entity_by_id(body.idClient);
        client.add_book(body.book);
        client.add_money_spent(body.book.getPrice());


    }


    @RequestMapping(value = "/addStatistics1Param", method = RequestMethod.POST)
    void get_books_sorted(@RequestBody String key){
        log.info("Method add statistics with 1 parameter was called");
        this.clientService.add_statistics(key);
    }



    @RequestMapping(value = "/addStatistics2Param", method = RequestMethod.POST)
    void get_books_sorted(@RequestBody ClientStatisticsModel model){
        log.info("Method add statistics with 2 parameters was called");
        this.clientService.add_statistics(model.get_name(),model.value());
    }



}
