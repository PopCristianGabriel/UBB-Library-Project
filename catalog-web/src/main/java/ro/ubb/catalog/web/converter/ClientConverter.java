package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.core.model.Student;
import ro.ubb.catalog.web.dto.ClientDto;
import ro.ubb.catalog.web.dto.StudentDto;

/**
 * Created by radu.
 */
@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {
    @Override
    public Client convertDtoToModel(ClientDto dto) {


        Client client = Client.builder()
                .fullName(dto.getFullName())
                .booksBought(dto.getBooksBought())
                .moneySpet(dto.getMoneySpet())
                .build();
        client.setId((dto.getId()));
        return client;
    }


    @Override
    public ClientDto convertModelToDto(Client client) {

        ClientDto dto = ClientDto.builder()
                .fullName(client.getFullName())
                .booksBought(client.getBooksBought())
                .moneySpet(client.getMoneySpet())
                .build();
        dto.setId(Long.valueOf(client.getId()));
        return dto;
    }
}
