package jmarijano_zadaca_3.Decorator;

import jmarijano_zadaca_3.Composite.PlanComponent;
import jmarijano_zadaca_3.org.SpecificShow;

public class RevenueShowDecoration extends ShowDecoration {

    public RevenueShowDecoration(PlanComponent planComponent) {
        super(planComponent);
    }

    @Override
    public void showData() {
        SpecificShow programEmisija = (SpecificShow) planComponent;

        System.out.format("|%11d|%-40s|%-20s|%-20s|%-20s|%33d|\n", programEmisija.getShow().getId(),
                programEmisija.getShow().getName(),
                programEmisija.getShow().getShowType().getName(),
                programEmisija.getStartTime(),
                programEmisija.getEndTime(),
                programEmisija.getShow().getShowType().getMaxAdvertisementDuration());

        System.out.format(String.format("%" + super.numberOfCharacters + "s", "-").replace(" ", "-") + "\n");

    }

}
