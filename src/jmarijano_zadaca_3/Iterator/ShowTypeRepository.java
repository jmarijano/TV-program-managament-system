package jmarijano_zadaca_3.Iterator;

import java.util.ArrayList;
import java.util.List;
import jmarijano_zadaca_3.Builder.DayComposite;
import jmarijano_zadaca_3.Composite.PlanComponent;
import jmarijano_zadaca_3.Composite.ProgramComposite;
import jmarijano_zadaca_3.org.SpecificShow;

public class ShowTypeRepository implements IteratorContainer {

    private List<PlanComponent> emisije;
    private int vrstaEmisije;

    public ShowTypeRepository(List<PlanComponent> emisije, int vrstaEmisije) {
        this.emisije = new ArrayList<>();
        for (PlanComponent planComponent : emisije) {
            this.emisije.add(planComponent.copy());
        }
        this.vrstaEmisije = vrstaEmisije;
    }

    @Override
    public Iterator getIterator() {
        return new ShowTypeIterator();
    }

    private class ShowTypeIterator implements Iterator<PlanComponent> {

        @Override
        public boolean hasNext() {
            if (emisije.isEmpty() || emisije == null) {
                return false;
            }
            return true;
        }

        @Override
        public PlanComponent getNext() {
            if (!hasNext()) {
                System.err.println("Nema composite elemenata");
                return null;
            }
            PlanComponent node = emisije.get(0);
            emisije.remove(0);
            int brojPrograma = emisije.size();
            if (node != null) {
                if (node instanceof ProgramComposite) {
                    ProgramComposite programComposite = (ProgramComposite) node;
                    for (PlanComponent planComponent : ((ProgramComposite) node).getChildren()) {
                        emisije.add(emisije.size() - brojPrograma, planComponent);
                        if (planComponent instanceof DayComposite) {
                            DayComposite danPrograma = (DayComposite) planComponent;
                            for (PlanComponent planComponent1 : danPrograma.getShows()) {
                                if (planComponent1 instanceof SpecificShow) {
                                    SpecificShow programEmisija = (SpecificShow) planComponent1;
                                    if (programEmisija.getShow().getShowType().getId() == vrstaEmisije) {
                                        emisije.add(emisije.size() - brojPrograma, planComponent1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return node;
        }

        @Override
        public void remove(PlanComponent t) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
