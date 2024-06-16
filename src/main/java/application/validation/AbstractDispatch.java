package application.validation;

import application.validation.chainsteps.Capsule;
import application.validation.chainsteps.Step;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractDispatch implements Dispatcher{

    protected Collection<Step> steps;

    @Override
    public void dispatch(Capsule capsule) {
        if (steps == null || steps.isEmpty()) {
            return;
        }
        Iterator<Step> it = steps.iterator();
        Step first = it.next();
        Step prev = first;
        while (it.hasNext()) {
            Step cur = it.next();
            prev.setNextStep(cur);
            prev = cur;
        }
        first.execute(capsule);
    }
}
