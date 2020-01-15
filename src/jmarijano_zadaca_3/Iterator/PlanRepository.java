package jmarijano_zadaca_3.Iterator;

import java.util.List;
import jmarijano_zadaca_3.Composite.PlanComponent;

public class PlanRepository implements IteratorContainer {

    private List<PlanComponent> component;

    public PlanRepository(List<PlanComponent> component) {
        this.component = component;
    }

    @Override
    public Iterator getIterator() {
        return new PlanIterator();
    }
    
    private class PlanIterator implements Iterator<PlanComponent> {

        int brojer = 0;

        @Override
        public boolean hasNext() {
            if (component.isEmpty() || brojer >= component.size()) {
                return false;
            }
            return true;
        }

        @Override
        public PlanComponent getNext() {
            if (!hasNext()) {
                return null;
            }
            PlanComponent output = component.get(brojer);
            brojer++;
            return output;
        }

        @Override
        public void remove(PlanComponent planComponent) {
            if (component.remove(planComponent)) {
                System.out.println("Emisija je izbrisana!");
            } else {
                System.out.println("Dogodila se greska prilikom brisanja emisije!");
            }
        }
    }
}
