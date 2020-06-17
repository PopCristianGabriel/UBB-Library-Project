package ro.ubb.catalog.core.model;

public class ValidatorException extends Throwable {
    public String message;
    public ValidatorException(String message){
        this.message = message;
    }

    public String get_message(){
        return this.message;
    }



}
