package application.validation.chainsteps;

import application.exceptions.ErroreQueryException;
import application.persistance.DBEsami;
import application.persistance.pojos.Appello;

public class AddOrUpdateAppelloStep extends AbstractStep {

    @Override
    public void execute(Capsule cap) {
        Long id = (Long) new DBEsami().update_or_add(cap.getObject(Utils.CAPSULE_KEY_APPELLO_CREATO, Appello.class));
        if( id == null){
            cap.setStatus(-1);
            cap.setException(new ErroreQueryException("Aggiunta appello non riuscita"));
            return;
        }
        cap.insertObject(Utils.CAPSULE_KEY_APPELLOID,id);
        if (nextStep != null)
            nextStep.execute(cap);
    }
}
