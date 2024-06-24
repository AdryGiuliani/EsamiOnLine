package application.exceptions;

public class NonPrenotatoException extends Exception {
    public NonPrenotatoException(String s) {
        super(s);
    }
    @Override
    public String toString() {
        return this.getMessage();
    }
}
