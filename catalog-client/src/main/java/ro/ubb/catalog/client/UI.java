package ro.ubb.catalog.client;


import ro.ubb.catalog.client.Controller.ControllerRest;
import ro.ubb.catalog.core.model.*;
import ro.ubb.catalog.core.repository.Sorting.*;
import lombok.Getter;
import lombok.Setter;
import ro.ubb.catalog.core.model.Validator;

import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Direction{

    public Direction(){

    }

    public int DESC(){
        return 1;
    }
}
@Getter
@Setter
public class UI  {

    protected Validator validator;
    private ControllerRest controller;
    private Long bookId;
    private Long clientId;
    public UI(ControllerRest controller) throws RemoteException {
        this.validator = new Validator();
        this.controller = controller;

    }



    public void UI_show_menu(){

        System.out.println("1 - add book");
        System.out.println("2 - add client");
        System.out.println("3 - remove book");
        System.out.println("4 - remove client");
        System.out.println("5 - update book");
        System.out.println("6 - update client");
        System.out.println("7 - check book");
        System.out.println("8 - check client");
        System.out.println("9 - show books");
        System.out.println("10 - show clients");
        System.out.println("11 - buy book");
        System.out.println("12 - filter client by name");
        System.out.println("13 - Sort clients by:");
        System.out.println("14 - Sort books by:");
        System.out.println("15 - Get most rented book:");
        System.out.println("16 - Get most active client");
        System.out.println("0 - exit");

    }


    /**
     *
     * @param book
     * adds book to repo
     */
    public void UI_add_book(ro.ubb.catalog.core.model.Book book) throws RemoteException {
        boolean ok = false;
        try{
            this.controller.Controller_save_book(book);
            this.setBookId(this.getBookId()+1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        if(ok == true){
            System.out.println("book added");

        }
    }


    /**
     *
     * @param client
     * adds a client to repo
     */



    public void UI_add_client(Client client) throws FileNotFoundException, ValidatorException, RemoteException {
        Client ok = null;
        try {
            this.controller.Controller_save_client(client);
            this.setClientId(this.getClientId()+1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        if(ok != null){
            System.out.println("client added");
            this.setClientId(this.getClientId()+1);
        }
    }

    /**
     *
     * @param id
     * deletes the client with the id:id
     */

    public void UI_delete_client(Long id){
        boolean ok = false;
        try{
            this.controller.Controller_delete_client(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        if(ok == true){
            System.out.println("client deleted");
        }
    }

    /**
     *
     * @param id
     * deletes the book with the id:id
     */
    public void UI_delete_book(Long id){
        boolean ok = false;
        try{
            this.controller.Controller_delete_book(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        if(ok == true){
            System.out.println("book deleted");
        }
    }

    /**
     *
     * @param book
     * updates the book
     */

    public void UI_update_book(Book book){
        boolean ok = false;
        try{
            this.controller.Controller_update_book(book);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        if(ok == true){
            System.out.println("book updated");
        }
    }

    /**
     *
     * @param client
     * updates the client
     */

    public void UI_update_client(Client client){
        boolean ok = false;
        try{
            this.controller.Controller_update_client(client);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        if(ok == true){
            System.out.println("client updated");
        }
    }

    public void UI_check_client(Long id){
        boolean ok = false;
        try{
            ok = this.controller.Controller_find_one_client(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(ok == true){
            System.out.println("client exists");
        }
    }

    public void UI_check_book(Long id){
        boolean ok = false;
        try{
            ok = this.controller.Controller_find_one_book(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(ok == true){
            System.out.println("book exists");
        }
    }

    /**
     * prints all clients
     */

    public void UI_print_clients() throws RemoteException {
        List<Client> clientList= this.controller.Controller_get_clients();

        for(Client c : clientList){
            System.out.println(c);
        }

    }

    /**
     * prints all books
     */

    public void UI_print_books() throws RemoteException {
        List<Book> bookList= this.controller.Controller_get_books();

        for(Book b : bookList){
            System.out.println(b);
        }

    }

    public void UiSortBooks() throws RemoteException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input the sorting criteria:");
        System.out.println("Input 1 for true and 0 for false");
        int id,Aname,title,year,price,directionInt;
        String idS="",autorNameS="",tileS="",yearS="",priceS="";

        System.out.println("By id?");
        id=scan.nextInt();
        if(id==1){
            idS="id";
        }

        System.out.println("By autorName?");
        Aname=scan.nextInt();
        if(Aname==1){
            autorNameS="autorName";
        }

        System.out.println("By title?");
        title=scan.nextInt();
        if(title==1){
            tileS="title";
        }

        System.out.println("By year?");
        year=scan.nextInt();
        if(year==1){
            yearS="year";
        }

        System.out.println("By price?");
        price=scan.nextInt();
        if(price==1){
            priceS="price";
        }

        System.out.println("Input the direction 0-ascending  1-descending");
        Direction direction=new Direction();
        directionInt=scan.nextInt();
        Sort sort;
        if(directionInt==0){
            sort=new Sort(autorNameS,tileS,yearS,priceS,idS);
        }
        else{
            sort=new Sort(direction.DESC(),autorNameS,tileS,yearS,priceS,idS);
        }

        ArrayList<Book> books=this.controller.Controller_get_books_sorted(sort);
        books.stream().forEach(b-> System.out.println(b));




    }

    public void UiSortClients() throws RemoteException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input the sorting criteria:");
        System.out.println("Input 1 for true and 0 for false");
        int id,name,money,directionInt;
        String idS="",nameS="",moneyS="";

        System.out.println("By id?");
        id=scan.nextInt();
        if(id==1){
            idS="id";
        }

        System.out.println("By name?");
        name=scan.nextInt();
        if(name==1){
            nameS="name";
        }

        System.out.println("By money spent?");
        money=scan.nextInt();
        if(money==1){
            moneyS="moneySpent";
        }

        System.out.println("Input the direction 0-ascending  1-descending");
        Direction direction=new Direction();
        directionInt=scan.nextInt();
        Sort sort;
        if(directionInt==0){
            sort=new Sort(idS,nameS,moneyS);
        }
        else{
            sort=new Sort(direction.DESC(),idS,nameS,moneyS);
        }

        ArrayList<Client> clients=this.controller.Controller_get_clients_sorted(sort);
        clients.stream().forEach(c-> System.out.println(c));


    }

    /**
     *
     * @param bookId
     * @return a new book object
     */

    public Book UI_read_book(Long bookId){
        Scanner scan = new Scanner(System.in);
        System.out.println("enter the author name:");

        String authorName = scan.nextLine();
        System.out.println("enter the title:");
        String title= scan.nextLine();
        int year = 0;
        int price = 0;
        System.out.println("enter the year:");
        year=validator.validateInt();

        System.out.println("enter the price:");
        price=validator.validateInt();
        Book returnedBook = new Book(bookId,authorName,title,year,price);
        //scan.close();
        return returnedBook;
    }

    public int UI_read_int_message(String message){
        System.out.println(message);
        Scanner scan = new Scanner(System.in);
        int integerToRead = 0;
        try{
            integerToRead = scan.nextInt();
        } catch (Exception e) {
            System.out.println("not an integer");
            //   scan.close();
            UI_read_int();
        }
        //  scan.close();
        return integerToRead;
    }

    public Long UI_read_long_message(String message){
        System.out.println(message);
        Scanner scan = new Scanner(System.in);
        Long integerToRead = null;
        try{
            integerToRead = scan.nextLong();
        } catch (Exception e) {
            System.out.println("not an integer");
            //   scan.close();
            UI_read_int();
        }
        //  scan.close();
        return integerToRead;
    }

    public void UI_buy_book(){
        Long bookId = UI_read_long_message("enter the book id:");
        Long clientId = UI_read_long_message("enter the client id:");
        boolean ok = false;
        try {
            this.controller.Controller_buy_book(clientId,bookId);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(ok){
            this.UI_delete_book(bookId);
        }
    }

    public Client UI_read_client(Long clientId){
        Scanner scan = new Scanner(System.in);
        System.out.println("enter the name:");
        String name = scan.nextLine();
        Client returedCliend = new Client(clientId,name);
        return returedCliend;
    }

    public String UI_read_string(){
        Scanner scan = new Scanner(System.in);
        System.out.println("enter the name:");
        String name = scan.nextLine();
        return name;
    }

    public int UI_read_int(){
        Scanner scan = new Scanner(System.in);
        System.out.println("enter an integer:");
        int integerToRead = 0;
        try{
            integerToRead = scan.nextInt();
        } catch (Exception e) {
            System.out.println("not an integer");
            //   scan.close();
            UI_read_int();
        }
        //  scan.close();
        return integerToRead;
    }
    public Long UI_read_long(){
        Scanner scan = new Scanner(System.in);
        System.out.println("enter an integer:");
        Long integerToRead = null;
        try{
            integerToRead = scan.nextLong();
        } catch (Exception e) {
            System.out.println("not an integer");
            //   scan.close();
            UI_read_int();
        }
        //  scan.close();
        return integerToRead;
    }



    public void UI_filter_by_name() throws RemoteException {
        String name = this.UI_read_string();
        ArrayList<Client> clientList = this.controller.Controller_get_clients_filtered(name);
        /*for(Client c : clientList){
            System.out.println(c);
        }*/

        clientList.stream().forEach(c-> System.out.println(c));
    }

    public void main() throws FileNotFoundException, ValidatorException, SQLException, RemoteException {

        Scanner scan = new Scanner(System.in);
        while(true){
            this.UI_show_menu();
            int value;
            value=validator.validate_0_13();
            if(value == 0) {
                this.controller.write_to_data_base();
                break;
            }
            else  if(value == 1){
                Book bookToAdd = this.UI_read_book(this.getBookId());
                if(bookToAdd != null){
                    this.UI_add_book(bookToAdd);
                }
            }

            else if(value == 2){
                Client clientToAdd = this.UI_read_client(this.getClientId());
                this.UI_add_client(clientToAdd);

            }
            else  if(value == 3){
                Long id = UI_read_long();
                UI_delete_book(id);
            }
            else  if(value == 4){
                Long id = UI_read_long();
                UI_delete_client(id);
            }
            else  if(value == 5){
                Long id = UI_read_long();
                Book bookToUpdate = UI_read_book(id);
                this.UI_update_book(bookToUpdate);
            }
            else if(value == 6){
                Long id = UI_read_long();
               Client clientToUpdate = UI_read_client(id);
                this.UI_update_client(clientToUpdate);
            }
            else   if(value == 7){
                Long id = UI_read_long();
                this.UI_check_book(id);
            }
            else if(value == 8){
                Long id = UI_read_long();
                this.UI_check_client(id);
            }
            else  if(value == 9){
                this.UI_print_books();
            }
            else if(value == 10){
                this.UI_print_clients();
            }
            else if(value == 11){
                this.UI_buy_book();
            }

            else if(value == 12){
                this.UI_filter_by_name();
            }
            else if(value==13){
                this.UiSortClients();
            }
            else if(value==14){
                this.UiSortBooks();
            }

            else if(value == 15){
                System.out.println(this.get_most_rented_book());
            }
            else if(value == 16){
                System.out.println(this.get_most_active_client());
            }


        }
        scan.close();
    }


    public Long get_nr_of_books() throws RemoteException {
        return this.controller.get_nr_of_books();
    }
    public Long get_nr_of_clients() throws RemoteException {
        return this.controller.get_nr_of_clients();
    }



    public String get_most_rented_book() throws RemoteException {
        return this.controller.Controller_get_most_purchased_book();
    }

    public void set_rented_books_in_statistics() throws RemoteException {
        this.controller.set_rented_books_in_statistics();
    }
    public void set_most_active_clients_in_statistics() throws RemoteException {
        this.controller.set_most_active_clients_in_statistics();
    }
    public String get_most_active_client() throws RemoteException {
        return this.controller.Controller_get_most_active_client();
    }

    public void setup() {
        this.setBookId(this.controller.get_nr_of_books());
        this.setClientId(this.controller.get_nr_of_clients());
    }
}

