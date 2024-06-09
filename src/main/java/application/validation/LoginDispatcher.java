package application.validation;

import com.google.protobuf.AbstractMessage;
import gen.javaproto.AllData;
import gen.javaproto.AllDataOrBuilder;
import gen.javaproto.Credentials;

/**
 * Validation of student credential, the cap is required to have a credential field
 */
public class LoginDispatcher implements Dispatcher{

    @Override
    public void dispatch(Capsule cap) {
        AuthStep astep = new AuthStep(true);
        astep.execute(cap);
    }
}
