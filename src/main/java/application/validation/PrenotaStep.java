package application.validation;


import application.exceptions.AppelloNonDisponibileException;
import application.persistance.DBEsami;
import application.persistance.pojos.Appello;

import javax.naming.NameNotFoundException;
import java.util.List;

public class PrenotaStep implements Step{

    private Step nextStep = null;
    private List<Appello> appellos;
    private boolean hasDisponibili = false;

    public PrenotaStep(boolean mantieniDisponibili){
        hasDisponibili = mantieniDisponibili;
    }

    /**
     *
     * @param cap assume che cap contenga the AppelloId to prenotare oltre alle credenziali utente
     */
    @Override
    public void execute(Capsule cap) {
        DBEsami db = new DBEsami();
        Appello app = new Appello();
        List<Appello> disponibili = db.getDisponibili(cap.getCredentials().getMat());
        if ( hasDisponibili) appellos = disponibili;
        db.carica(app,cap.getID());
        if (app == null){
            cap.setException(new NameNotFoundException("nessun appello corrispondente trovato"));
            return;
        }
        if (!disponibili.contains(app)) {
            cap.setException(new AppelloNonDisponibileException("appello non prenotabile"));
            return;
        }
        if (nextStep != null)
            nextStep.execute(cap);
    }

    @Override
    public void setNextStep(Step nextStep) {
        this.nextStep = nextStep;
    }

}


