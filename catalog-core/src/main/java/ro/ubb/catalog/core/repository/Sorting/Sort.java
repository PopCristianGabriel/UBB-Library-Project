package ro.ubb.catalog.core.repository.Sorting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.ubb.catalog.core.model.*;
import ro.ubb.catalog.core.model.Entity;

import java.util.ArrayList;
import java.util.Comparator;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sort<ID> implements Comparator<Entity<ID>> ,java.io.Serializable{
    int direction;
    private ArrayList<String> fields;


    public Sort(int direction,String...fields) {
        this.direction=direction;
        this.fields=new ArrayList<>();
        for(String f:fields)
            this.fields.add(f);

    }

    public Sort(String...fields){
        this.direction=0;
        this.fields=new ArrayList<>();
        for(String f:fields)
            this.fields.add(f);
    }

    @Override
    public int compare(Entity<ID> o1, Entity<ID> o2) {

        String sortBy1="";
        String sortBy2="";

        if(o1 instanceof Book && o2 instanceof Book){
            for(String fieldsString:this.fields) {
                if (fieldsString.contains("autorName")) {
                    sortBy2 += ((Book) o2).getAutorName();
                    sortBy1 += ((Book) o1).getAutorName();
                }

                else if (fieldsString.contains("title")) {
                    sortBy1 += ((Book) o1).getTitle();
                    sortBy2 += ((Book) o2).getTitle();
                }

                else if (fieldsString.contains("year")){
                    sortBy1+=((Book) o1).getYear();
                    sortBy2+=((Book) o2).getYear();
                }
                else if(fieldsString.contains("price")){
                    sortBy1+=((Book) o1).getPrice();
                    sortBy2+=((Book) o2).getPrice();
                }
                else if(fieldsString.contains("id")){
                    sortBy1+=Long.toString(Long.toString(((Book) o1).getid()).length())+((Book) o1).getid();
                    sortBy2+=Long.toString(Long.toString(((Book) o2).getid()).length())+((Book) o2).getid();
                }
            }

        }
        else if(o1 instanceof Client && o2 instanceof Client){
            for(String fieldsString:this.fields) {
                if(fieldsString.contains("id")){
                    sortBy1+=Long.toString(Long.toString(((Client) o1).getid()).length())+((Client) o1).getId();
                    sortBy2+=Long.toString(Long.toString(((Client) o2).getid()).length())+((Client) o2).getId();

                }
                else if(fieldsString.contains("name")){
                    sortBy1+=((Client) o1).getFullName();
                    sortBy2+=((Client) o2).getFullName();
                }
                else if(fieldsString.contains("moneySpent")){
                    sortBy1+=Integer.toString(Integer.toString(((Client) o1).getMoneySpet()).length())+((Client) o1).getMoneySpet();
                    sortBy2+=Integer.toString(Integer.toString(((Client) o2).getMoneySpet()).length())+((Client) o2).getMoneySpet();
                }
            }
        }
        if(this.direction==0) {


            if (sortBy1.compareTo(sortBy2) > 0)
                return 1;
            else return -1;
        }
        else{
            if (sortBy1.compareTo(sortBy2) > 0)
                return -1;
            else return 1;
        }
    }
}