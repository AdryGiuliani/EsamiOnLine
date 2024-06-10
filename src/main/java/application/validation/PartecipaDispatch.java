package application.validation;

import application.validation.chainsteps.AuthStep;
import application.validation.chainsteps.Capsule;
import application.validation.chainsteps.PartecipaStep;
import org.checkerframework.checker.units.qual.A;

public class PartecipaDispatch implements Dispatcher{

    @Override
    public void dispatch(Capsule capsule) {
        AuthStep authStep = new AuthStep(true);
        PartecipaStep partecipaStep = new PartecipaStep();
        authStep.setNextStep(partecipaStep);
        authStep.execute(capsule);
    }
}
