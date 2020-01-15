package jmarijano_zadaca_3.Memento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jmarijano_zadaca_3.Builder.DayComposite;
import jmarijano_zadaca_3.Composite.PlanComponent;
import jmarijano_zadaca_3.Composite.ProgramComposite;
import jmarijano_zadaca_3.Iterator.Iterator;
import jmarijano_zadaca_3.Iterator.IteratorContainer;
import jmarijano_zadaca_3.Iterator.PlanRepository;
import jmarijano_zadaca_3.org.SpecificShow;

public class Memento {

    private int id;
    private LocalDateTime localDateTime;
    private List<PlanComponent> components = new ArrayList<>();

    public Memento(List<PlanComponent> components, int id, LocalDateTime localDateTime) {
        IteratorContainer iteratorContainer = new PlanRepository(components);
        for (Iterator<PlanComponent> iteratorPrograma = iteratorContainer.getIterator(); iteratorPrograma.hasNext();) {
            PlanComponent program = iteratorPrograma.getNext();
            PlanComponent newProgram = program.copy();
            ProgramComposite programComposite = (ProgramComposite) program;
            ProgramComposite newProgramComposite = new ProgramComposite();
            newProgramComposite.setId(programComposite.getId());
            iteratorContainer = new PlanRepository(programComposite.getChildren());
            for (Iterator<PlanComponent> iteratorDana = iteratorContainer.getIterator(); iteratorDana.hasNext();) {
                PlanComponent dan = iteratorDana.getNext();
                DayComposite dayComposite = (DayComposite) dan;
                DayComposite newDayComposite = new DayComposite();
                newDayComposite.setDay(dayComposite.getDay());
                newDayComposite.setEndTime(dayComposite.getEndTime());
                newDayComposite.setStartTime(dayComposite.getStartTime());
                iteratorContainer = new PlanRepository(dayComposite.getShows());
                for (Iterator<PlanComponent> iteratorEmisija = iteratorContainer.getIterator(); iteratorEmisija.hasNext();) {
                    PlanComponent emisija = iteratorEmisija.getNext();
                    SpecificShow newEmisija = (SpecificShow) emisija;
                    SpecificShow newEmisijaCopy = newEmisija.copy();
                    newDayComposite.add(newEmisijaCopy);

                }
                newProgramComposite.add(newDayComposite);
            }
            this.components.add(newProgramComposite);
        }
        this.id = id;
        this.localDateTime = localDateTime;
    }

    public List<PlanComponent> getSavedState() {
        return components;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

}
