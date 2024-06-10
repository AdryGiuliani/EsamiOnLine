package application.validation;

import application.validation.chainsteps.AuthStep;
import application.validation.chainsteps.CancellaPrenotazioneStep;
import application.validation.chainsteps.Capsule;

public class CancellaDispatch implements Dispatcher{
    @Override
    public void dispatch(Capsule capsule) {
        AuthStep authStep= new AuthStep(true);
        CancellaPrenotazioneStep cancelStep= new CancellaPrenotazioneStep();
        authStep.setNextStep(cancelStep);
        authStep.execute(capsule);
    }
}
