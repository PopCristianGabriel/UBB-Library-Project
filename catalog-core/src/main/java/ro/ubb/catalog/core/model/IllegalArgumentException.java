package ro.ubb.catalog.core.model;

public class IllegalArgumentException extends Throwable {
    public String message;
    IllegalArgumentException(String message){
        this.message = message;
    }

    public String get_message(){
        return this.message;
    }
}
