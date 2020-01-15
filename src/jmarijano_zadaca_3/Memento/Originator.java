package jmarijano_zadaca_3.Memento;

import java.time.LocalDateTime;
import java.util.List;
import jmarijano_zadaca_3.Composite.PlanComponent;

public class Originator {

    private int id;
    private List<PlanComponent> components;
    private LocalDateTime localDateTime;

    public void set(List<PlanComponent> components, int id) {
        this.components = components;
        this.id = id;
    }

    public Memento saveToMemento() {
        return new Memento(components, this.id, LocalDateTime.now());
    }

    public void restoreFromMemento(Memento memento, int id) {
        if (memento.getId() == id) {
            this.components = memento.getSavedState();
            this.id = memento.getId();
            this.localDateTime = memento.getLocalDateTime();
        }
    }

    public List<PlanComponent> getComponents() {
        return components;
    }

}
