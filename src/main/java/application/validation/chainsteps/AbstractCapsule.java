package application.validation.chainsteps;

import java.util.Map;

public abstract class AbstractCapsule implements Capsule {

    protected Map<String,Object> payload;
    private int status = 1;
    private Exception exception = null;

    @Override
    public void insertObject(String key, Object value) {
        payload.put(key, value);
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz) {
        return (T) payload.get(key);
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "{" +
                "status=" + status +
                ", exception=" + exception +
                '}';
    }
}
