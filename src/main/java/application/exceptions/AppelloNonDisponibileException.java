package application.exceptions;

public class AppelloNonDisponibileException extends Exception{
    public AppelloNonDisponibileException(String message){
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
