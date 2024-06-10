package application.validation.chainsteps;

import application.persistance.DBEsami;
import application.persistance.Database;
import application.persistance.pojos.Options;
import application.persistance.pojos.Risultato;
import application.persistance.pojos.Student;

import static application.persistance.util.Utils.KEY_OPZIONI;


/**
 * carica il risultato dell'appello nel relativo studente, contrassegnandolo come superato o meno
 */
public class CompletaAppelloStep extends  AbstractStep{

    @Override
    public void execute(Capsule cap) {
        Student s = cap.getObject(Utils.CAPSULE_KEY_STUDENTE, Student.class);
        Risultato res = cap.getObject(Utils.CAPSULE_KEY_RISULTATO, Risultato.class);
        DBEsami db = new DBEsami();
        Options options = db.carica(Options.class,KEY_OPZIONI);
        res.setSuperato(res.getPunteggio() >= options.getMinVoto());
        s.getCompletato().add(res);
        db.update_or_add(s);
        if (nextStep!=null)
            nextStep.execute(cap);
    }
}
