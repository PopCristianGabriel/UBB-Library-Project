package ro.ubb.catalog.core.model;



import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@javax.persistence.Entity(name = "Client")
public class Client extends Entity<Long> implements Serializable {
    private String fullName;
    public ArrayList<Book> booksBought;
    private int moneySpet;


    /**
     *
     * @param clientId
     * @param fullName
     */



    public Client(Long clientId, String fullName) {
        super(clientId);
        this.fullName = fullName;
        this.booksBought = new ArrayList<Book>();
        this.moneySpet = 0;

    }

    @Override
    public void  setEntity(Entity e){
        if (e instanceof Client){
            this.setId(((Client) e).getId());
            this.fullName=((Client) e).getFullName();
            this.booksBought=(((Client) e).get_books_bought());
            this.moneySpet=((Client) e).get_money_spent();
        }

    }

    public void setMoneySpet(int money){
        this.moneySpet=money;
    }

    // @XmlAttribute
    public ArrayList<Book> getBooksBought() {
        return booksBought;
    }

    public int getMoneySpet() {
        return moneySpet;
    }

    public ArrayList<Book> get_books_bought(){
        return this.booksBought;
    }

    public void reset_books_bought(){
        this.booksBought = new ArrayList<>();
    }

    public int get_money_spent(){
        return this.moneySpet;
    }


    public void add_book(Book b){
        this.booksBought.add(b);
    }
    public void add_money_spent(int price){
        this.moneySpet += price;
    }

    public String getFullName() {
        return fullName;
    }

    public Long getid(){
        return super.getId();
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        Client c = (Client) o;
        return c.getId().intValue() == this.getId().intValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }

    @Override
    public String toString() {
        return "Client{" +
                "Client id = " + this.getId() + " " +
                "fullName='" + fullName + '\'' +
                ", booksBought=" + booksBought +
                ", moneySpet=" + moneySpet +
                '}';
    }
}
