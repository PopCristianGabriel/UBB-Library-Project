package ro.ubb.catalog.web.dto;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class BookDto extends BaseDto {
    private String autorName;
    private String title;
    private int year;
    private int price;


    public BookDto(Long id, String autor, String title, int year, int price){
        super(id);
        this.autorName = autor;
        this.title = title;
        this.year = year;
        this.price = price;
    }


}
