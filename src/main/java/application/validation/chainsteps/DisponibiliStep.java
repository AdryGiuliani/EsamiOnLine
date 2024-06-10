package application.validation.chainsteps;

import application.exceptions.AppelloNonDisponibileException;
import application.exceptions.ErroreQueryException;
import application.persistance.DBEsami;
import application.persistance.pojos.Appello;
import gen.javaproto.Credentials;

import javax.naming.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Lo step esegue le seguenti operazioni:
 * -restituisce la lista di appelli disponibili per l'utente corrente
 * -eventualmente restituisce l'errore che è avvenuto interrompendo la catena
 */
public class DisponibiliStep extends AbstractStep{

    @Override
    public void execute(Capsule cap) {
        DBEsami db = new DBEsami();
        List<Appello> disponibili;
        try {
            disponibili = db.getDisponibili(cap.getObject(Utils.CAPSULE_KEY_CREDENZIALI, Credentials.class).getMat());
        }catch (Exception e){
            cap.setStatus(-1);
            cap.setException(new ErroreQueryException("errore nel recupero degli appelli disponibili, riprova"));
            return;
        }
        cap.insertObject(Utils.CAPSULE_KEY_DISPONIBILI,disponibili);
        if (nextStep != null)
            nextStep.execute(cap);
    }
}
