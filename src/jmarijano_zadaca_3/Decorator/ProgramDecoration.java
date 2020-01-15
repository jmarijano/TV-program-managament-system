package jmarijano_zadaca_3.Decorator;

import jmarijano_zacaca_3.Visitor.Visitor;
import jmarijano_zadaca_3.Composite.PlanComponent;
import jmarijano_zadaca_3.Composite.ProgramComposite;
import jmarijano_zadaca_3.Helpers.StringHelper;

public class ProgramDecoration extends PlanComponentDecorator {

    private PlanComponent planComponent;

    public ProgramDecoration(PlanComponent planComponent) {
        this.planComponent = planComponent;
    }

    @Override
    public void showData() {
        ProgramComposite programComposite = (ProgramComposite) planComponent;
        System.out.format(String.format("%" + super.numberOfCharacters + "s", "-").replace(" ", "-") + "\n");

        System.out.println("|" + StringHelper.getCenteredString("Program: " + programComposite.getId(), super.numberOfCharacters) + "|");

    }

    @Override
    public int accept(Visitor visitor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
