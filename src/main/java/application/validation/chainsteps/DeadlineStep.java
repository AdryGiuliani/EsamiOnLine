package application.validation.chainsteps;

import application.persistance.DBEsami;
import application.persistance.pojos.Options;

public class DeadlineStep extends AbstractStep {
    @Override
    public void execute(Capsule cap) {
        Options opt = new DBEsami().caricaOpzioni();
        cap.insertObject(Utils.CAPSULE_KEY_OPTIONS_DEADLINE,opt.getDeadline_millis());
        cap.insertObject(Utils.CAPSULE_KEY_OPTIONS_SOGLIA,opt.getMinVoto());
        if (nextStep!=null)
            nextStep.execute(cap);
    }
}
