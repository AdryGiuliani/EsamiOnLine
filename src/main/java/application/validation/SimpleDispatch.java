package application.validation;

import application.validation.chainsteps.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleDispatch extends AbstractDispatch {

    public SimpleDispatch(Step... steps) {
            this.steps = Arrays.asList(steps);
    }
}
