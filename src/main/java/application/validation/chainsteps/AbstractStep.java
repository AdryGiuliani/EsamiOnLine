package application.validation.chainsteps;

public abstract class AbstractStep implements Step{

    protected Step nextStep = null;
    @Override
    public void setNextStep(Step nextStep) {
        this.nextStep = nextStep;
    }
}
