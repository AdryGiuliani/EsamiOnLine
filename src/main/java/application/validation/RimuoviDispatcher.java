package application.validation;

import application.validation.chainsteps.AdminAuthStep;
import application.validation.chainsteps.Capsule;
import application.validation.chainsteps.CheckTimeStep;
import application.validation.chainsteps.RemoveAppelloStep;

public class RimuoviDispatcher implements Dispatcher {
    @Override
    public void dispatch(Capsule capsule) {
        AdminAuthStep auth = new AdminAuthStep();
        CheckTimeStep checkTime = new CheckTimeStep();
        RemoveAppelloStep remove = new RemoveAppelloStep();
        auth.setNextStep(checkTime);
        checkTime.setNextStep(remove);
        auth.execute(capsule);
    }
}
