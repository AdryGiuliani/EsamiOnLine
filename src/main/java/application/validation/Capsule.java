package application.validation;

import application.persistance.pojos.Pojo;
import gen.javaproto.Credentials;

public interface Capsule {
    public Credentials getCredentials();
    public void setCredentials(Credentials credentials);
    public Object getID();
    public void setID(Object ID);
    public Pojo getPojo();
    public void setPojo(Pojo pojo);
    public Exception getException();
    public void setException(Exception exception);
}
