package application.validation.chainsteps;

import application.exceptions.DeadlineException;
import application.exceptions.NonPrenotatoException;
import application.persistance.DBEsami;
import application.persistance.pojos.Appello;
import application.persistance.pojos.Domanda;
import application.persistance.pojos.Risultato;
import application.persistance.pojos.Student;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * Lo step verifica se la richiesta è stata effettuata nellla finestra di tempo disponibile
 * (se ci sono state disconnessioni lo studente può rientrare a patto che non sia finito il tempo)
 * e garantisce l'autorizzazione o ritorna un errore se non è possibile partecipare
 * (Nota: è prerequisito che l'appello sia tra i prenotati dello studente pertanto sono ritenuti validi
 * i controlli sulla prenotazione (partecipare ad esami già completati etc))
 */
public class PartecipaStep extends AbstractStep {

    @Override
    public void execute(Capsule cap) {
        DBEsami db = new DBEsami();
        Appello app = db.carica(Appello.class,cap.getObject(Utils.CAPSULE_KEY_APPELLOID,Appello.class));
        Student s = cap.getObject(Utils.CAPSULE_KEY_STUDENTE, Student.class);
        if (!s.getPrenotazioni().contains(app)){
            System.out.println(app.getNome()+">Impossibile partecipare, l'appello non è stato prenotato");
            cap.setStatus(-1);
            cap.setException(new NonPrenotatoException("L'appello non è stato prenotato"));
            return;
        }
        if(s.getCompletato().stream()
                .anyMatch(
                        risultato -> risultato.isSuperato() && app.equalsSoft(risultato.getCompleted_appello())
                ))
        {
            System.out.println(app.getNome()+">Impossibile partecipare, appello già superato");
            cap.setStatus(-1);
            cap.setException(new NonPrenotatoException("L'appello è già stato superato"));
            return;
        }
        Timestamp deadline = new Timestamp(app.getData_ora().getTime()+app.getDurata_minuti()* TimeUnit.MINUTES.toMillis(1));
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (!(now.after(app.getData_ora()) && now.before(deadline))){
            System.out.println(app.getNome()+">Impossibile partecipare, l'appello deve iniziare o è già terminato");
            cap.setStatus(-1);
            cap.setException(new DeadlineException("L'appello deve iniziare o è già terminato"));
            return;
        }
        cap.insertObject(Utils.CAPSULE_KEY_DOMANDE,db.getDomande((Long)app.getId()));
        if (nextStep!=null)
            nextStep.execute(cap);

    }
}
