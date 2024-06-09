package application.validation;

public interface Capsule {

    public void insertObject(String key, Object obj);
    public <T> T getObject(String key, Class<T> clazz);
    public int getStatus();
    public void setStatus(int status);
    public Exception getException();
    public void setException(Exception exception);


}
