package jmarijano_zadaca_3.Decorator;

import jmarijano_zacaca_3.Visitor.Visitor;
import jmarijano_zadaca_3.Composite.PlanComponent;

public abstract class PlanComponentDecorator implements PlanComponent {

    protected int numberOfCharacters = 150;

    @Override
    public abstract void showData();

    @Override
    public PlanComponent getComponent() {
        return this;
    }

    @Override
    public PlanComponent copy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int accept(Visitor visitor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setParent(PlanComponent parent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
