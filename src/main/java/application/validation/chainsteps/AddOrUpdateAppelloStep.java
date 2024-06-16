package application.validation.chainsteps;

import application.exceptions.ErroreQueryException;
import application.persistance.DBEsami;
import application.persistance.pojos.Appello;

public class AddOrUpdateAppelloStep extends AbstractStep {

    @Override
    public void execute(Capsule cap) {
        if(!new DBEsami().update_or_add(cap.getObject(Utils.CAPSULE_KEY_APPELLO_CREATO, Appello.class))){
            cap.setStatus(-1);
            cap.setException(new ErroreQueryException("Aggiunta appello non riuscita"));
            return;
        };
        if (nextStep != null)
            nextStep.execute(cap);
    }
}
