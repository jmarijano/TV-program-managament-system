package jmarijano_zacaca_3.Visitor;

import jmarijano_zadaca_3.Builder.DayComposite;
import jmarijano_zadaca_3.Composite.ProgramComposite;
import jmarijano_zadaca_3.org.SpecificShow;

public interface Visitor {

    int visit(DayComposite dayComposite);

    int visit(ProgramComposite programComposite);

    int visit(SpecificShow specificShow);

    int visitComposite(CompositeCollection compositeCollection);
}
