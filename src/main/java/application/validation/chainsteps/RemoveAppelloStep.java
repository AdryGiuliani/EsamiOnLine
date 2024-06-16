package application.validation.chainsteps;

import application.exceptions.ErroreQueryException;
import application.persistance.DBEsami;
import application.persistance.pojos.Appello;

public class RemoveAppelloStep extends AbstractStep {

    @Override
    public void execute(Capsule cap) {
        if(!new DBEsami().delete(new Appello(), cap.getObject(Utils.CAPSULE_KEY_APPELLOID, Appello.class))){
            cap.setStatus(-1);
            cap.setException(new ErroreQueryException("Rimozione appello non riuscita"));
            return;
        };
        if (nextStep != null)
            nextStep.execute(cap);
    }
}
