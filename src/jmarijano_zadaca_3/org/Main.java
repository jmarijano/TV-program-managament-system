package jmarijano_zadaca_3.org;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import jmarijano_zadaca_3.Builder.BuilderClient;
import jmarijano_zadaca_3.Builder.DayComposite;
import jmarijano_zadaca_3.Composite.PlanComponent;
import jmarijano_zadaca_3.Composite.ProgramComposite;
import jmarijano_zadaca_3.Helpers.ConfigurationHelper;
import jmarijano_zadaca_3.Helpers.Menu;
import jmarijano_zadaca_3.Iterator.Iterator;
import jmarijano_zadaca_3.Iterator.IteratorContainer;
import jmarijano_zadaca_3.Iterator.PlanRepository;
import jmarijano_zadaca_3.Observer.Subject;
import jmarijano_zadaca_3.Singleton.TVHouse;

public class Main {

    public static void main(String[] args) throws Exception {
        boolean kraj = false;
        if (!ConfigurationHelper.hasValidNumberOfArguments(args)) {
            System.out.println("Nisu uneseni svi parametri!");
            System.exit(-1);
        }
        List<String> argumenti = Arrays.asList(args);
        if (!ConfigurationHelper.hasValidFlags(argumenti)) {
            System.out.println("Neispravno uneseni parametri");
            System.exit(-1);
        }
        ConfigurationHelper.setupRoles(argumenti);
        ConfigurationHelper.setupPersons(argumenti);
        ConfigurationHelper.setupShowTypes(argumenti);
        ConfigurationHelper.setupShows(argumenti);
        ConfigurationHelper.setupPrograms(argumenti);
        ConfigurationHelper.setupSpecificShows();

        BuilderClient builderClient = new BuilderClient();
        List<PlanComponent> listaPrograma = builderClient.getProgram();

        for (Subject subject : TVHouse.getInstance().getSubjects()) {
            PersonRole personRole = (PersonRole) subject;
            IteratorContainer iteratorContainer = new PlanRepository(listaPrograma);
            for (Iterator<PlanComponent> iteratorPrograma = iteratorContainer.getIterator(); iteratorPrograma.hasNext();) {
                PlanComponent planComponent = iteratorPrograma.getNext();
                ProgramComposite programComposite = (ProgramComposite) planComponent;
                iteratorContainer = new PlanRepository(programComposite.getChildren());
                for (Iterator<PlanComponent> iteratorDana = iteratorContainer.getIterator(); iteratorDana.hasNext();) {
                    PlanComponent pc = iteratorDana.getNext();
                    DayComposite dayComposite = (DayComposite) pc;
                    iteratorContainer = new PlanRepository(dayComposite.getShows());
                    for (Iterator<PlanComponent> iteratorEmisija = iteratorContainer.getIterator(); iteratorEmisija.hasNext();) {
                        PlanComponent pc1 = iteratorEmisija.getNext();
                        SpecificShow specificShow = (SpecificShow) pc1;
                        for (Subject subjectShow : specificShow.getPersonRoles()) {
                            PersonRole izEmisije = (PersonRole) subjectShow;
                            if (izEmisije.getPerson().getId() == personRole.getPerson().getId()
                                    && izEmisije.getRole().getId() == personRole.getRole().getId()) {
                                subject.subscribeObserver(specificShow);
                            }
                        }
                    }
                }
            }
        }
        List<Subject> kae=TVHouse.getInstance().getSubjects();
        System.out.println("kae");
        while (!kraj) {
            Menu menu = new Menu();
            menu.makeMenu();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int uneseno = 0;
            try {
                uneseno = Integer.parseInt(br.readLine());
            } catch (Exception e) {
                System.out.println("Unesite broj");
            }
            switch (uneseno) {
                case 1:
                    try {
                        menu.printFirstOption(listaPrograma);
                    } catch (IOException | NumberFormatException e) {
                        System.out.println("Unesite broj!");
                    }
                    break;
                case 2:
                    try {
                        menu.printSecondOption(listaPrograma);
                    } catch (IOException | NumberFormatException e) {
                        System.out.println("Unesite broj!");
                    }
                    break;
                case 3:
                    try {
                        menu.printThirdOption(listaPrograma);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        menu.printFourthOption(listaPrograma);
                    } catch (Exception e) {
                        System.err.println("Dogodila se pogreska");
                    }
                    break;
                case 5:
                    try {
                        menu.printFifthOption(listaPrograma);
                    } catch (Exception e) {
                        System.err.println("Dogodila se pogreska");
                        e.printStackTrace();
                    }

                    break;
                case 6:
                    try {
                        menu.printSixthOption();
                    } catch (Exception e) {
                    }
                    break;
                case 7:
                    try {
                        listaPrograma = menu.printSeventhOption();
                    } catch (Exception e) {
                        System.out.println("NE postoji taj ID");
                    }
                    break;
                case 8:
                    menu.printEigthOption(listaPrograma);
                    break;
                case 9:
                    break;
                case 10:
                    kraj = true;
                    break;
                default:
                    System.out.println("Ne postoji ta opcija!");
                    break;
            }
        }
    }

}
