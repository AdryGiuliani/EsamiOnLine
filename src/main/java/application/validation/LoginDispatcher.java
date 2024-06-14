package application.validation;

import application.validation.chainsteps.AuthStep;
import application.validation.chainsteps.Capsule;
import application.validation.chainsteps.DisponibiliStep;

/**
 * Validation of student credential, the cap is required to have a credential field
 */
public class LoginDispatcher implements Dispatcher{
    @Override
    public void dispatch(Capsule cap) {
        AuthStep astep = new AuthStep(true);
        DisponibiliStep ds = new DisponibiliStep();
        astep.execute(cap);
        astep.setNextStep(ds);
    }
}
