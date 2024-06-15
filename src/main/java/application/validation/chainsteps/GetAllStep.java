package application.validation.chainsteps;

import application.persistance.DBEsami;

public class GetAllStep extends AbstractStep {

    @Override
    public void execute(Capsule cap) {
        DBEsami db = new DBEsami();
        cap.insertObject(Utils.CAPSULE_KEY_ALLAPPELLI, db.getAllAppelli());
        if (nextStep != null)
            nextStep.execute(cap);
    }
}
