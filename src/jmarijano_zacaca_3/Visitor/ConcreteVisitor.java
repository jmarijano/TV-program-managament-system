package jmarijano_zacaca_3.Visitor;

import jmarijano_zadaca_3.Builder.DayComposite;
import jmarijano_zadaca_3.Composite.PlanComponent;
import jmarijano_zadaca_3.Composite.ProgramComposite;
import jmarijano_zadaca_3.org.SpecificShow;

public class ConcreteVisitor implements Visitor {

    @Override
    public int visit(DayComposite dayComposite) {
        return 0;
    }

    @Override
    public int visit(ProgramComposite programComposite) {
        return 0;
    }

    @Override
    public int visit(SpecificShow specificShow) {
        return specificShow.getShow().getShowType().getMaxAdvertisementDuration();
    }

    @Override
    public int visitComposite(CompositeCollection compositeCollection) {
        int output = 0;
        if (compositeCollection.getComponent() instanceof DayComposite) {
            DayComposite programComposite = (DayComposite) compositeCollection.getComponent();
            for (PlanComponent planComponent : programComposite.getShows()) {
                output += planComponent.accept(this);
            }
        }
        return output;
    }

}
