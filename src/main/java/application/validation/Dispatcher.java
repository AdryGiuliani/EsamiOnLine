package application.validation;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.Message;

public interface Dispatcher {
    public void dispatch(Capsule capsule);
}
