package application.validation;

import application.validation.chainsteps.AuthStep;
import application.validation.chainsteps.Capsule;

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
