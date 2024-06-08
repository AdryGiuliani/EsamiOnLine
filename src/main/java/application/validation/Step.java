package application.validation;

import application.exceptions.CredenzialiErrateException;

public interface Step {
    public void execute(Capsule cap);
    public void setNextStep(Step nextStep);
}
