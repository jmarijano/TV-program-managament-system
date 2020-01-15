package jmarijano_zadaca_3.Memento;

import java.util.ArrayList;
import java.util.List;
import jmarijano_zadaca_3.Composite.PlanComponent;

public class Caretaker {

    private static int brojSpremanja = 0;
    private static List<Object> savedStates = new ArrayList<>();
    private static Originator originator = new Originator();

    public static void addMemento(List<PlanComponent> planComponents) {
        originator.set(planComponents, brojSpremanja);
        savedStates.add(originator.saveToMemento());
        brojSpremanja++;
    }

    public static List<Object> getSavedStates() {
        return savedStates;
    }

    public static List<PlanComponent> returnMemento(int id) throws Exception {
        if (originator.getComponents().isEmpty() || originator.getComponents() == null) {
            throw new Exception();
        }
        for (Object object : savedStates) {
            Memento memento = (Memento) object;
            if (memento.getId() == id) {
                originator.restoreFromMemento(memento, id);
                return originator.getComponents();
            }
        }
        throw new Exception();
    }

    public static int getNumberOfSavedStates() {
        return savedStates.size();
    }

    public static void printSavedStates() {
        System.out.println("Broj pohranjivanja: " + brojSpremanja);
        for (Object savedState : savedStates) {
            Memento memento = (Memento) savedState;
            System.out.println("ID spremanja: " + memento.getId() + " Vrijeme spremanja: " + memento.getLocalDateTime());

        }
    }

}
