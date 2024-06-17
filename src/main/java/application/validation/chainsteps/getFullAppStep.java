package application.validation.chainsteps;

import application.persistance.DBEsami;
import application.persistance.pojos.Appello;
import application.persistance.pojos.Domanda;

import java.util.List;

public class getFullAppStep extends AbstractStep {

    @Override
    public void execute(Capsule cap) {
        DBEsami db = new DBEsami();
        long id = cap.getObject(Utils.CAPSULE_KEY_APPELLOID, Long.class);
        Appello app_lazy = db.carica(Appello.class, id);
        List<Domanda> domande = db.getDomande(id);
        app_lazy.setDomande(domande);
        cap.insertObject(Utils.CAPSULE_KEY_APPELLO_CREATO, app_lazy);
        if (nextStep!=null)
            nextStep.execute(cap);
    }
}
