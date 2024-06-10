package application.validation.chainsteps;

import java.io.Serializable;

public interface Capsule extends Serializable {

    public void insertObject(String key, Object obj);
    public <T> T getObject(String key, Class<T> clazz);
    public int getStatus();
    public void setStatus(int status);
    public Exception getException();
    public void setException(Exception exception);


}
