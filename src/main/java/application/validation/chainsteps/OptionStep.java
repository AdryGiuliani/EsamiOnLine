package application.validation.chainsteps;

import application.persistance.DBEsami;
import application.persistance.pojos.Options;

public class OptionStep extends AbstractStep {

    public enum OP{
        READ, UPDATE
    }
    private OP operation;

    public OptionStep(OP op) {
        this.operation = op;
    }
    @Override
    public void execute(Capsule cap) {
        switch (operation) {
            case READ: cap.insertObject(Utils.CAPSULE_KEY_OPTIONS,new DBEsami().caricaOpzioni());
            case UPDATE: new DBEsami().update_or_add(cap.getObject(Utils.CAPSULE_KEY_OPTIONS, Options.class));
        }
    }
}
