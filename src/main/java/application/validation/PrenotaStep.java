package application.validation;


import application.exceptions.AppelloNonDisponibileException;
import application.exceptions.ErroreQueryException;
import application.persistance.DBEsami;
import application.persistance.pojos.Appello;
import gen.javaproto.Credentials;

import javax.naming.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PrenotaStep implements Step{

    private Step nextStep = null;
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
        List<Appello> disponibili = new ArrayList<>();
        try {
            disponibili = db.getDisponibili(cap.getObject(Utils.CAPSULE_KEY_CREDENZIALI, Credentials.class).getMat());
        }catch (Exception e){
            cap.setException(new ErroreQueryException("errore nella prenotazione, riprova"));
            return;
        }
        if (hasDisponibili) cap.insertObject(Utils.CAPSULE_KEY_DISPONIBILI,disponibili);
        String idAppello = cap.getObject(Utils.CAPSULE_KEY_APPELLOID,String.class);
        if (idAppello == null){
            cap.setStatus(-1);
            cap.setException(new AppelloNonDisponibileException("appello non prenotabile"));
            return;
        }
        db.carica(app,idAppello);
        if (app == null){
            cap.setStatus(-1);
            cap.setException(new NameNotFoundException("nessun appello corrispondente trovato"));
            return;
        }
        if (!disponibili.contains(app)) {
            cap.setStatus(-1);
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


