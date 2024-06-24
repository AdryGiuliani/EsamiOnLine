package application.exceptions;

public class CredenzialiErrateException extends Exception
{
    public CredenzialiErrateException(String message){
        super(message);
    }
    @Override
    public String toString() {
        return this.getMessage();
    }
}
