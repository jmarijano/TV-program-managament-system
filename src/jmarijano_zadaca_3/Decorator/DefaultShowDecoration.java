package jmarijano_zadaca_3.Decorator;

import jmarijano_zadaca_3.Composite.PlanComponent;
import jmarijano_zadaca_3.Observer.Subject;
import jmarijano_zadaca_3.org.PersonRole;
import jmarijano_zadaca_3.org.SpecificShow;

public class DefaultShowDecoration extends ShowDecoration {

    public DefaultShowDecoration(PlanComponent planComponent) {
        super(planComponent);
    }

    @Override
    public void showData() {

        SpecificShow programEmisija = (SpecificShow) planComponent;

        int brojac = 1;
        if (programEmisija.getPersonRoles() != null && programEmisija.getPersonRoles().size() > 0) {
            int brojOsoba = programEmisija.getPersonRoles().size();
            int sredina = 0;
            if (brojOsoba % 2 == 0) {
                sredina = brojOsoba / 2;
            } else {
                sredina = brojOsoba / 2 + 1;
            }
            for (Subject subject : programEmisija.getPersonRoles()) {
                if (subject instanceof PersonRole) {
                    PersonRole personRole = (PersonRole) subject;
                    if (brojac == sredina) {
                        System.out.format("|%11d|%-40s|%-20s|%-20s|%-20s|%-33s|\n", programEmisija.getShow().getId(),
                                programEmisija.getShow().getName(),
                                programEmisija.getShow().getShowType().getName(),
                                programEmisija.getStartTime(),
                                programEmisija.getEndTime(),
                                personRole.getPerson().getNameAndSurname() + "-"
                                + personRole.getRole().getDescription());
                    } else {
                        System.out.format("|%11s|%-40s|%-20s|%-20s|%-20s|%-33s|\n", " ",
                                " ",
                                " ",
                                " ",
                                " ",
                                personRole.getPerson().getNameAndSurname() + "-"
                                + personRole.getRole().getDescription());
                    }
                }
                brojac++;
            }
        } else {
            System.out.format("|%11d|%-40s|%-20s|%-20s|%-20s|%-33s|\n", programEmisija.getShow().getId(),
                    programEmisija.getShow().getName(),
                    programEmisija.getShow().getShowType().getName(),
                    programEmisija.getStartTime(),
                    programEmisija.getEndTime(),
                    " ");
        }
        System.out.format(String.format("%" + super.numberOfCharacters + "s", "-").replace(" ", "-") + "\n");

    }
}
