package ro.ubb.catalog.web.dto;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;
import ro.ubb.catalog.core.model.Book;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class ClientDto extends BaseDto {
    private String fullName;
    public ArrayList<Book> booksBought;
    private int moneySpet;

    public ClientDto(Long id, String name){
        super(id);
        this.fullName = name;
    }
    public ClientDto(Long id, String name,int moneySpet){
        super(id);
        this.fullName = name;
        this.booksBought = new ArrayList<Book>();
        this.moneySpet = moneySpet;
    }
}
