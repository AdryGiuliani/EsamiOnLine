package application.validation.chainsteps;

import application.persistance.DBEsami;

public class DeadlineStep extends AbstractStep {
    @Override
    public void execute(Capsule cap) {
        cap.insertObject(Utils.CAPSULE_KEY_OPTIONS_DEADLINE,new DBEsami().caricaOpzioni().getDeadline_millis());
        if (nextStep!=null)
            nextStep.execute(cap);
    }
}
