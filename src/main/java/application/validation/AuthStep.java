package application.validation;

import application.exceptions.CredenzialiErrateException;
import application.persistance.DBEsami;
import application.persistance.Database;
import application.persistance.pojos.Student;
import gen.javaproto.Credentials;

public class AuthStep implements Step{

    private Step nextStep = null;
    private Student stud = null;
    private boolean hasStud = false;

    public AuthStep(boolean mantainStudent) {
        hasStud = mantainStudent;
    }

    @Override
    public void execute(Capsule cap){
        Credentials c = cap.getCredentials();
        Database db = new DBEsami();
        Student s = new Student();
        db.carica(s, c.getMat());
        if (s == null){
            cap.setException(new CredenzialiErrateException("matricola errata"));
            return;
        } else if (!s.getCf().equals(c.getCf().toUpperCase())) {
            cap.setException(new CredenzialiErrateException("Codice fiscale errato"));
            return;
        }
        if (hasStud)
            stud = s;
        if (nextStep!=null)
            nextStep.execute(cap);
    }

    @Override
    public void setNextStep(Step nextStep) {
        this.nextStep = nextStep;
    }
}
