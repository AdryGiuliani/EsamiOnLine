package application.validation.chainsteps;

import application.persistance.DBEsami;
import application.persistance.Database;
import application.persistance.pojos.Options;
import application.persistance.pojos.Risultato;
import application.persistance.pojos.Student;
import gen.javaproto.CompletedAppello;
import gen.javaproto.Credentials;

import static application.persistance.util.Utils.KEY_OPZIONI;


/**
 * carica il risultato dell'appello nel relativo studente, contrassegnandolo come superato o meno
 */
public class CompletaAppelloStep extends  AbstractStep{

    @Override
    public void execute(Capsule cap) {
        Credentials c = cap.getObject(Utils.CAPSULE_KEY_CREDENZIALI, Credentials.class);
        CompletedAppello infoCompletato = cap.getObject(Utils.CAPSULE_KEY_RISULTATO, CompletedAppello.class);
        new DBEsami().salvaRisultato(c.getMat(), infoCompletato.getIdAppello(), infoCompletato.getPunteggio());

        if (nextStep!=null)
            nextStep.execute(cap);
    }
}
