package application.validation.chainsteps;

import application.exceptions.CredenzialiErrateException;
import application.persistance.DBEsami;
import application.persistance.pojos.Options;
import gen.javaproto.Credentials;

import static application.validation.chainsteps.Utils.CAPSULE_KEY_CREDENZIALI;

public class AdminAuthStep extends AbstractStep{

    @Override
    public void execute(Capsule cap) {
        Credentials c = cap.getObject(CAPSULE_KEY_CREDENZIALI,Credentials.class);
        DBEsami db = new DBEsami();
        Options s = db.carica(Options.class, application.persistance.util.Utils.KEY_OPZIONI);
        if (!s.getAdmin_usrn().equals(c.getMat()) || !s.getAdmin_pwd().equals(c.getCf())){
            cap.setStatus(-1);
            cap.setException(new CredenzialiErrateException("Credenziali errate"));
            return;
        }
        if (nextStep!=null)
            nextStep.execute(cap);
    }
}
