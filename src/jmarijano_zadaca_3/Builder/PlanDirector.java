package jmarijano_zadaca_3.Builder;

import java.time.LocalTime;
import java.util.List;
import jmarijano_zadaca_3.org.SpecificShow;

public class PlanDirector {

    private final PlanBuilder planBuilder;

    public PlanDirector(PlanBuilder planBuilder) {
        this.planBuilder = planBuilder;
    }

    public DayComposite construct(int danUTjednu,
            LocalTime pocetak, LocalTime kraj,
            List<SpecificShow> emisije) {
        return planBuilder
                .buildDay(danUTjednu)
                .buildStartTime(pocetak)
                .buildEndTime(kraj)
                .buildEmisijePrograma(emisije)
                .buildDayComposite();
    }
}
