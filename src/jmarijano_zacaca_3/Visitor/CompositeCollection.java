package jmarijano_zacaca_3.Visitor;

import jmarijano_zadaca_3.Composite.PlanComponent;

public class CompositeCollection {

    private PlanComponent component;

    public CompositeCollection(PlanComponent planComponent) {
        this.component = planComponent;
    }

    public PlanComponent getComponent() {
        return component;
    }

}
