package ro.ubb.catalog.core.model;

import lombok.*;
import ro.ubb.catalog.core.model.BaseEntity;
import ro.ubb.catalog.core.model.Book;
import ro.ubb.catalog.core.model.Client;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class LinkEntity extends BaseEntity {
    Client client;
    Book book;

}
