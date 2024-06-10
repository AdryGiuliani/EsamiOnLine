package application.validation;

import application.validation.chainsteps.AuthStep;
import application.validation.chainsteps.Capsule;
import application.validation.chainsteps.PrenotaStep;

public class PrenotazioneDispatch implements Dispatcher{

    @Override
    public void dispatch(Capsule capsule) {
        AuthStep authStep = new AuthStep(true);
        PrenotaStep prenotaStep = new PrenotaStep(true);
        authStep.setNextStep(prenotaStep);
        authStep.execute(capsule);
        //se è presente un errore durante la prenotazione manda indietro anche la lista dei disponibili aggiornata
        //nell'ottica di risparmiare chiamate al server abbondando con le informazioni
    }
}
