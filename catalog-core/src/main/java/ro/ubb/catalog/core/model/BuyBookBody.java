package ro.ubb.catalog.core.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class BuyBookBody {
    public Long idClient;
    public Book book;

    public BuyBookBody(Long idClient, Book book) {

        this .idClient = idClient;
        this.book = book;
    }
}
