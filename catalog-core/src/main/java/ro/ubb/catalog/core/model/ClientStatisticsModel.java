package ro.ubb.catalog.core.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ClientStatisticsModel {
    private String name;
    private int value;

    public String get_name(){
        return this.name;
    }
    public int value(){
        return this.value;
    }

}
