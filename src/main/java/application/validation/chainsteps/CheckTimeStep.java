package application.validation.chainsteps;

import application.exceptions.DeadlineException;
import application.persistance.DBEsami;
import application.persistance.pojos.Appello;
import application.persistance.pojos.Options;

import java.sql.Time;

public class CheckTimeStep extends AbstractStep {
    @Override
    public void execute(Capsule cap) {
        Long oldAppid = cap.getObject(Utils.CAPSULE_KEY_APPELLOID, Long.class);
        DBEsami db = new DBEsami();
        Appello oldApp = db.carica(Appello.class,oldAppid);
        Options opt = new DBEsami().caricaOpzioni();
        if (System.currentTimeMillis() + opt.getDeadline_millis() >=oldApp.getData_ora().getTime()){
            cap.setStatus(-1);
            cap.setException(new DeadlineException("Impossibile modificare appello, troppo vicino alla scadenza"));
            return;
        }
        if (nextStep != null)
            nextStep.execute(cap);
    }
}
