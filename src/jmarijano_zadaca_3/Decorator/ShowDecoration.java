package jmarijano_zadaca_3.Decorator;

import jmarijano_zadaca_3.Composite.PlanComponent;

public abstract class ShowDecoration extends PlanComponentDecorator {

    protected PlanComponent planComponent;

    public ShowDecoration(PlanComponent planComponent) {
        this.planComponent = planComponent;
    }
}
