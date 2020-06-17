package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
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
public class ClientServiceImpl implements ClientService{

    @Autowired
    SortingRepository<Long,Client> clients;

    @Override
    public List<Client> getAllClients() throws FileNotFoundException, ValidatorException, ParserConfigurationException {
        return (List<Client>) this.clients.findAll();
    }

    @Override
    public Client saveClient(Client client) throws ParserConfigurationException, ValidatorException, IOException {
        this.clients.save(client);
        return client;
    }

    @Override
    public Client updateClient(Client client) throws ParserConfigurationException, ValidatorException, IOException {
        this.clients.update(client);
        return client;
    }

    @Override
    public void deleteClient(Long id) throws IOException, ParserConfigurationException {
        this.clients.delete(id);
    }

    @Override
    public Client get_entity_by_id(Long id) {
        return (Client) this.clients.get_entity_by_id(id);
    }

    @Override
    public void add_statistics(String key, Integer value) {
        this.clients.add_statistic(key,value);
    }

    @Override
    public void add_statistics(String key) {
        this.clients.add_statistic(key);
    }

    @Override
    public String get_most() {
        return this.clients.get_most();
    }

    @Override
    public Boolean find_one(Long id) throws ValidatorException {
        return this.clients.findOne(id) != null;
    }

    @Override
    public ArrayList<Client> get_clients_sorted(Sort sort) {
        return (ArrayList<Client>) this.clients.findAll(sort);
    }

    @Override
    public void truncate_tables() throws SQLException {
        this.clients.truncateTables();
    }

    @Override
    public void commit() {
        this.clients.commit();
    }

    @Override
    public void flush() {
    this.clients.flush();
    }

    @Override
    public void write_to_data_base() throws SQLException {
        this.clients.writeToDataBase();
    }
}
