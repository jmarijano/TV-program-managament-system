package jmarijano_zadaca_3.Composite;

import jmarijano_zacaca_3.Visitor.Visitor;

public interface PlanComponent {

    PlanComponent getComponent();

    PlanComponent copy();

    void showData();

    int accept(Visitor visitor);
    
    void setParent(PlanComponent parent);
}
