package application.validation;

import application.validation.chainsteps.*;

public class GetAllDispatcher implements Dispatcher {
    @Override
    public void dispatch(Capsule capsule) {
        AdminAuthStep authStep = new AdminAuthStep();
        GetAllStep getAllStep = new GetAllStep();
        authStep.setNextStep(getAllStep);
        authStep.execute(capsule);
    }
}
