package application.validation.chainsteps;


import application.exceptions.AppelloNonDisponibileException;
import application.exceptions.ErroreQueryException;
import application.persistance.DBEsami;
import application.persistance.pojos.Appello;
import application.persistance.pojos.Risultato;
import application.persistance.pojos.Student;
import gen.javaproto.Credentials;

import javax.naming.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lo step esegue le seguenti operazioni:
 * -verifica se l'appello indicato è tra i disponibili per l'utente corrente
 * -effettua la prenotazione restituendo l'esito nella capsula oppure:
 * -se non prenotabile restituisce l'errore che è avvenuto interrompendo la catena
 */
public class PrenotaStep extends AbstractStep{

    private boolean hasDisponibili = false;

    public PrenotaStep(boolean mantieniDisponibili){
        hasDisponibili = mantieniDisponibili;
    }

    /**
     *
     * @param cap assume che cap contenga l' AppelloId da prenotare oltre alle credenziali utente
     */
    @Override
    public void execute(Capsule cap) {
        DBEsami db = new DBEsami();
        Appello app = new Appello();
        List<Appello> disponibili = new ArrayList<>();
        try {
            disponibili = db.getDisponibili(cap.getObject(Utils.CAPSULE_KEY_CREDENZIALI, Credentials.class).getMat());
        }catch (Exception e){
            e.printStackTrace();
            cap.setStatus(-1);
            cap.setException(new ErroreQueryException("errore nella prenotazione, riprova"));
            return;
        }
        if (hasDisponibili) cap.insertObject(Utils.CAPSULE_KEY_DISPONIBILI,disponibili);
        Long idAppello = cap.getObject(Utils.CAPSULE_KEY_APPELLOID,Long.class);
        if (idAppello == null){
            cap.setStatus(-1);
            cap.setException(new AppelloNonDisponibileException("appello non prenotabile"));
            return;
        }
        app = db.carica(Appello.class,idAppello);
        if (app == null){
            cap.setStatus(-1);
            cap.setException(new NameNotFoundException("nessun appello corrispondente trovato"));
            return;
        }

        if (!disponibili.contains(app)){
            cap.setStatus(-1);
            cap.setException(new AppelloNonDisponibileException("appello non prenotabile"));
            return;
        }
        //è necessario effettuare l'operazione in un contesto di persistenza per non dover ricorrere
        // ad un caricamento EAGER dal database
        db.prenota(cap.getObject(Utils.CAPSULE_KEY_CREDENZIALI,Credentials.class).getMat(),idAppello);
        if (nextStep != null)
            nextStep.execute(cap);
    }
}


