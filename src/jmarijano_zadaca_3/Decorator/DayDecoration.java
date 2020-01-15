package jmarijano_zadaca_3.Decorator;

import jmarijano_zadaca_3.Composite.PlanComponent;

public abstract class DayDecoration extends PlanComponentDecorator {

    protected PlanComponent planComponent;

    public DayDecoration(PlanComponent planComponent) {
        this.planComponent = planComponent;
    }

}
