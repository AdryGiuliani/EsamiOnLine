package application.validation.chainsteps;

public interface Step {
    public void execute(Capsule cap);
    public void setNextStep(Step nextStep);
}
