package application.validation.chainsteps;

import application.exceptions.DeadlineException;
import application.persistance.DBEsami;
import application.persistance.pojos.Appello;
import application.persistance.pojos.Options;
import application.persistance.pojos.Student;

import javax.naming.NameNotFoundException;
import java.sql.Timestamp;
import java.util.List;

import static application.persistance.util.Utils.KEY_OPZIONI;

public class CancellaPrenotazioneStep extends AbstractStep{

    @Override
    public void execute(Capsule cap) {
        DBEsami db = new DBEsami();
        Student s = cap.getObject(Utils.CAPSULE_KEY_STUDENTE, Student.class);
        List<Appello> prenotati = s.getPrenotazioni();
        long appId = cap.getObject(Utils.CAPSULE_KEY_APPELLOID, Long.class);
        Appello check = new Appello();
        check.setId(appId);
        int i = prenotati.indexOf(check);
        if (i<0){
            System.out.println(s.getMat()+">Prenotazione non trovata");
            cap.setStatus(-1);
            cap.setException(new NameNotFoundException("Appello da rimuovere non prenotato"));
            return;
        }
        check = prenotati.get(i);
        Options opt = db.carica(Options.class, KEY_OPZIONI);
        Timestamp deadline = new Timestamp(System.currentTimeMillis()+opt.getDeadline_millis());
        if (deadline.after(check.getData_ora())) {
            System.out.println(s.getMat()+">Impossibile rimuovere prenotazione, tempo scaduto");
            cap.setStatus(-1);
            cap.setException(new DeadlineException("Appello da rimuovere non prenotato"));
            return;
        }
        prenotati.remove(i);
        db.update_or_add(s);
        if (nextStep!=null)
            nextStep.execute(cap);
    }
}
