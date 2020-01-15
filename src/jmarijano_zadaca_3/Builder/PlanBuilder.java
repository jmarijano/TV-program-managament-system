package jmarijano_zadaca_3.Builder;

import java.time.LocalTime;
import java.util.List;
import jmarijano_zadaca_3.org.SpecificShow;

public interface PlanBuilder {

    PlanBuilder buildDay(int day);

    PlanBuilder buildStartTime(LocalTime startTime);

    PlanBuilder buildEndTime(LocalTime endTime);

    PlanBuilder buildEmisijePrograma(List<SpecificShow> shows);

    DayComposite buildDayComposite();

}
