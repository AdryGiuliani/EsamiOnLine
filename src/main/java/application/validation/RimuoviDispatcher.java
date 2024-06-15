package application.validation;

import application.persistance.DBEsami;
import application.persistance.pojos.Appello;
import application.persistance.util.Utils;
import application.validation.chainsteps.AdminAuthStep;
import application.validation.chainsteps.Capsule;
import application.validation.chainsteps.CheckTimeStep;
import application.validation.chainsteps.RemoveStep;

public class RimuoviDispatcher implements Dispatcher {
    @Override
    public void dispatch(Capsule capsule) {
        AdminAuthStep auth = new AdminAuthStep();
        CheckTimeStep checkTime = new CheckTimeStep();
        RemoveStep remove = new RemoveStep();
        auth.setNextStep(checkTime);
        checkTime.setNextStep(remove);
        auth.execute(capsule);
    }
}
