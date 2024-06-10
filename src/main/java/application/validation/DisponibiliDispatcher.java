package application.validation;

import application.validation.chainsteps.AuthStep;
import application.validation.chainsteps.Capsule;
import application.validation.chainsteps.DisponibiliStep;

public class DisponibiliDispatcher implements Dispatcher {
    @Override
    public void dispatch(Capsule capsule) {
        AuthStep authStep = new AuthStep(false);
        DisponibiliStep disponibiliStep = new DisponibiliStep();
        authStep.setNextStep(disponibiliStep);
        authStep.execute(capsule);
    }
}
