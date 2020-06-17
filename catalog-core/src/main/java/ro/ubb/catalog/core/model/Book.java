package ro.ubb.catalog.core.model;



import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@javax.persistence.Entity(name = "Book")

public class Book extends Entity<Long> implements Serializable {

    private String autorName;

    private String title;

    private int year;

    private int price;




    public Long getid(){
        return super.getId();
    }


    public String getAutorName() {
        return autorName;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }

    public void setAutorName(String autorName) {
        this.autorName = autorName;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     *
     * @param bookId
     * @param autorName
     * @param title
     * @param year
     * @param price
     */


    public Book( Long bookId, String autorName, String title, int year, int price) {
        super(bookId);
        this.autorName = autorName;
        this.title = title;
        this.year = year;
        this.price = price;

    }

    /**
     *
     * @return price of the book
     */
    @Override
    public void setEntity(Entity e){
        if(e instanceof Book){
            this.setId(((Book) e).getId());
            this.autorName=((Book) e).getAutorName();
            this.title=((Book) e).getTitle();
            this.year=((Book) e).getYear();
            this.price=((Book) e).getPrice();
        }

    }




    @Override
    public boolean equals(Object o) {
        if(this.getId() == null){
            return false;
        }
        Book b = (Book) o;
        return b.getId().intValue() == this.getId().intValue();
    }

    @Override
    public String toString() {
        return "Book{" +
                "Book id = " + this.getId() + " " +
                "autorName='" + autorName + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }

    public void setClientId(Integer integer) {

    }

    public void copy(Book entit) {
        this.setAutorName(entit.getAutorName());
        this.setPrice(entit.getPrice());

    }
}
