package application.validation;

import application.persistance.pojos.Pojo;
import gen.javaproto.Credentials;

public class CapsuleValidate implements  Capsule {

    private Credentials credenziali;
    private Object id;
    private Pojo pojo;
    private Exception except = null;

    public CapsuleValidate(Credentials credenziali, Pojo pojo, Object id) {
        this.credenziali = credenziali;
        this.pojo = pojo;
        this.id = id;
    }

    public CapsuleValidate() {}


    @Override
    public Credentials getCredentials() {
        return credenziali;
    }

    @Override
    public void setCredentials(Credentials credentials) {
        this.credenziali = credentials;
    }

    @Override
    public Object getID() {
        return id;
    }

    @Override
    public void setID(Object ID) {
        this.id = ID;
    }

    @Override
    public Pojo getPojo() {
        return pojo;
    }

    @Override
    public void setPojo(Pojo pojo) {
        this.pojo = pojo;
    }

    @Override
    public Exception getException() {
        return except;
    }

    @Override
    public void setException(Exception exception) {
        except = exception;
    }
}
