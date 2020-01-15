package jmarijano_zadaca_3.Builder;

import java.util.ArrayList;
import java.util.List;
import jmarijano_zadaca_3.Composite.PlanComponent;
import jmarijano_zadaca_3.Composite.ProgramComposite;
import jmarijano_zadaca_3.Singleton.TVHouse;
import jmarijano_zadaca_3.org.Program;
import jmarijano_zadaca_3.org.SpecificShow;

public class BuilderClient {

    public BuilderClient() {
    }

    public List<PlanComponent> getProgram() {
        int id = 1;
        List<PlanComponent> output = new ArrayList<>();
        for (Program program : TVHouse.getInstance().getPrograms()) {
            ProgramComposite programComposite = new ProgramComposite();
            programComposite.setId(program.getId());
            for (int i = 1; i <= 7; i++) {
                PlanBuilder planBuilder = new ConcreteBuilder();
                PlanDirector planDirector = new PlanDirector(planBuilder);
                DayComposite planProduct = planDirector.construct(i,
                        program.getStartTime(),
                        program.getEndTime(), program.getSpecificShows()
                );
                for (PlanComponent planComponent : planProduct.getShows()) {
                    if (planComponent instanceof SpecificShow) {
                        SpecificShow specificShow = (SpecificShow) planComponent;
                        specificShow.setId(id);
                        specificShow.setParent(planProduct);
                        id++;
                    }
                }
                planProduct.setParent(programComposite);
                programComposite.add(planProduct);
            }
            output.add(programComposite);

        }
        System.out.println("kae");
        System.out.println("kae");
        return output;
    }
}
