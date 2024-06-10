package application.validation;

import application.validation.chainsteps.AuthStep;
import application.validation.chainsteps.Capsule;
import application.validation.chainsteps.CompletaAppelloStep;

public class ConcludiDispatch implements Dispatcher{

    @Override
    public void dispatch(Capsule capsule) {
        AuthStep authStep = new AuthStep(true);
        CompletaAppelloStep completaStep = new CompletaAppelloStep();
        authStep.setNextStep(completaStep);
        authStep.execute(capsule);
    }
}
