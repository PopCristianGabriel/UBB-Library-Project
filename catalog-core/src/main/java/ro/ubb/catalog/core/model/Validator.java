package ro.ubb.catalog.core.model;

import java.util.Scanner;

public class Validator {
    Scanner scan2;


    public Validator() {
        scan2 = new Scanner(System.in);

    }

    public boolean validate_book(Book b) {
        if (b.getYear() < 0) {
            return false;
        }
        if (b.getPrice() < 0) {
            return false;
        }
        return true;
    }

    public boolean validate_client(Client c) {
        if (c.get_money_spent() < 0) {
            return false;
        }
        if (c.getFullName().isEmpty()) {
            return false;
        }
        return true;
    }

    public int validateInt() {
        boolean ok = false;
        int var = 0;
        while (!ok || var < 0) {
            ok = true;
            try {
                var = scan2.nextInt();
            } catch (Exception e) {
                scan2.next();
                ok = false;
                System.out.println("Input an integer!");
            }
        }
        return var;

    }

    public int validatePozitiv() {
        int generic = scan2.nextInt();
        while (generic < 0) {
            System.out.println("Input a pozitiv integer!");
            generic = scan2.nextInt();
        }
        return generic;

    }

    public int validate1_5() throws Exception {
        boolean ok = false;
        int var = 0;
        while (!ok || var < 1 || var > 5) {
            ok = true;
            try {
                var = scan2.nextInt();
            } catch (Exception e) {
                scan2.next();
                ok = false;
                System.out.println("Input an integer!");
            }
        }
        return var;


    }

    public int validate_0_13() {
        boolean ok = false;
        int var = 0;
        while (!ok || var < 0 || var > 17) {
            ok = true;
            try {
                var = scan2.nextInt();
            } catch (Exception e) {
                scan2.next();
                ok = false;
                System.out.println("Input an integer!");
            }
        }
        return var;


    }
}
