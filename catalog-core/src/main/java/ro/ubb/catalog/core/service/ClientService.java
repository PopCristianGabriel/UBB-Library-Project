package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.core.model.Student;
import ro.ubb.catalog.core.model.ValidatorException;
import ro.ubb.catalog.core.repository.Sorting.Sort;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ClientService {
    List<Client> getAllClients() throws FileNotFoundException, ValidatorException, ParserConfigurationException;

    Client saveClient(Client client) throws ParserConfigurationException, ValidatorException, IOException;

    Client updateClient( Client client) throws ParserConfigurationException, ValidatorException, IOException;

    void deleteClient(Long id) throws IOException, ParserConfigurationException;

    Client get_entity_by_id(Long id);
    void add_statistics(String key, Integer value);
    void add_statistics(String key);
    String get_most();
    Boolean find_one(Long id) throws ValidatorException;
    ArrayList<Client> get_clients_sorted(Sort sort);
    void truncate_tables() throws SQLException;
    void commit();
    void flush();
    void write_to_data_base() throws SQLException;
}
