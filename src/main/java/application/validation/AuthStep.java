package application.validation;

import application.exceptions.CredenzialiErrateException;
import application.persistance.DBEsami;
import application.persistance.Database;
import application.persistance.pojos.Student;
import gen.javaproto.Credentials;

import static application.validation.Utils.CAPSULE_KEY_CREDENZIALI;
import static application.validation.Utils.CAPSULE_KEY_STUDENTE;

public class AuthStep implements Step{

    private Step nextStep = null;
    private boolean saveStud = false;

    public AuthStep(boolean mantainStudent) {
        saveStud = mantainStudent;
    }

    @Override
    public void execute(Capsule cap){
        Credentials c = cap.getObject(CAPSULE_KEY_CREDENZIALI,Credentials.class);
        Database db = new DBEsami();
        Student s = null;
        db.carica(s, c.getMat());
        if (s == null){//typo intellij, s viene aggiornato per riferimento in db.carica
            cap.setException(new CredenzialiErrateException("matricola errata, utente non presente nel sistema"));
            cap.setStatus(-1);
            return;
        } else if (!s.getCf().equals(c.getCf().toUpperCase())) {
            cap.setStatus(-1);
            cap.setException(new CredenzialiErrateException("Codice fiscale errato"));
            return;
        }
        if (saveStud)
            cap.insertObject(CAPSULE_KEY_STUDENTE,s);
        if (nextStep!=null)
            nextStep.execute(cap);
    }

    @Override
    public void setNextStep(Step nextStep) {
        this.nextStep = nextStep;
    }
}
