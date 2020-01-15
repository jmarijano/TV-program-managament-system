/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmarijano_zadaca_3.Decorator;

import jmarijano_zadaca_3.Builder.DayComposite;
import jmarijano_zadaca_3.Composite.PlanComponent;
import jmarijano_zadaca_3.Helpers.StringHelper;

/**
 *
 * @author morbi_000
 */
public class DefaultDayDecoration extends DayDecoration{

    public DefaultDayDecoration(PlanComponent planComponent) {
        super(planComponent);
    }
    @Override
    public void showData() {
        DayComposite danPrograma = (DayComposite) planComponent;
        System.out.format(String.format("%" + super.numberOfCharacters + "s", "-").replace(" ", "-") + "\n");

        System.out.format("|%-49s|%-49s|%-49s|\n",
                StringHelper.getCenteredString("Dan: " + danPrograma.getDay(), 49),
                StringHelper.getCenteredString("Početak: " + danPrograma.getStartTime(), 49),
                StringHelper.getCenteredString("Kraj: " + danPrograma.getEndTime(), 49));

        System.out.format(String.format("%" + super.numberOfCharacters + "s", "-").replace(" ", "-") + "\n");
        System.out.format("|%11s|%-40s|%-20s|%-20s|%-20s|%-33s|\n",
                StringHelper.getCenteredString("ID emisije", 11),
                StringHelper.getCenteredString("Naziv emisije", 40),
                StringHelper.getCenteredString("Vrsta emisije", 20),
                StringHelper.getCenteredString("Vrijeme početka", 20),
                StringHelper.getCenteredString("Vrijeme kraja", 20),
                StringHelper.getCenteredString("Osobe i uloge",33));

        System.out.format(String.format("%" + super.numberOfCharacters + "s", "-").replace(" ", "-") + "\n");
    }

}
