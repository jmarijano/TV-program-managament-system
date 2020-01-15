/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmarijano_zadaca_3.Helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import jmarijano_zacaca_3.Visitor.CompositeCollection;
import jmarijano_zacaca_3.Visitor.ConcreteVisitor;
import jmarijano_zacaca_3.Visitor.Visitor;
import jmarijano_zadaca_3.Builder.DayComposite;
import jmarijano_zadaca_3.COR.Handler;
import jmarijano_zadaca_3.COR.HighRevenueIncomeHandler;
import jmarijano_zadaca_3.COR.LowRevenueIncomeHandler;
import jmarijano_zadaca_3.COR.NormalRevenueHandler;
import jmarijano_zadaca_3.Composite.PlanComponent;
import jmarijano_zadaca_3.Composite.ProgramComposite;
import jmarijano_zadaca_3.Decorator.RevenueDayDecoration;
import jmarijano_zadaca_3.Decorator.RevenueShowDecoration;
import jmarijano_zadaca_3.Iterator.Iterator;
import jmarijano_zadaca_3.Iterator.IteratorContainer;
import jmarijano_zadaca_3.Iterator.PlanRepository;
import jmarijano_zadaca_3.Iterator.ShowTypeRepository;
import jmarijano_zadaca_3.Memento.Caretaker;
import jmarijano_zadaca_3.Observer.Subject;
import jmarijano_zadaca_3.Singleton.TVHouse;
import jmarijano_zadaca_3.org.Person;
import jmarijano_zadaca_3.org.PersonRole;
import jmarijano_zadaca_3.org.Role;
import jmarijano_zadaca_3.org.SpecificShow;

public class Menu {

    public Menu() {
    }

    public void makeMenu() {
        System.out.println("Unesite 1. za odabir programa i dana u tjednu za koji želite ispis vremenskog plana");
        System.out.println("Unesite 2. za odabir programa i dana u tjednu za ispis potencijalnog prihoda od reklama");
        System.out.println("Unesite 3. za odabir vrste emisije za ispis po svim programima i danima");
        System.out.println("Unesite 4. za odabir osobe, postojeće uloge i novu ulogu za emisije");
        System.out.println("Unesite 5. za brisanje emisije iz tjednog plana programa");
        System.out.println("Unesite 6. za ispisivanje broja pohranjivanja podataka o programima TV kuće");
        System.out.println("Unesite 7. za vracanje podataka na odredeni broj pohranjivanja");
        System.out.println("Unesite 8. za informaciju isplativosti reklamiranja za odabrani program i dan.");
        System.out.println("Unesite 9. za promjenu pogleda");
        System.out.println("Unesite 10. za kraj programa");
        System.out.print("Odabir: ");
    }

    public void printFirstOption(List<PlanComponent> listaPrograma) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Unesite ID programa: ");
        int uneseniIdProgram = Integer.parseInt(br.readLine());
        System.out.print("Unesite dan u tjednu(broj dana):");
        int uneseniDan = Integer.parseInt(br.readLine());
        System.out.println("\n");
        IteratorContainer iteratorContainer = new PlanRepository(listaPrograma);
        for (Iterator<PlanComponent> iteratorPrograma = iteratorContainer.getIterator(); iteratorPrograma.hasNext();) {
            PlanComponent planComponent = iteratorPrograma.getNext();
            ProgramComposite programComposite = (ProgramComposite) planComponent;
            if (programComposite.getId() == uneseniIdProgram) {
                planComponent.showData();
                iteratorContainer = new PlanRepository(programComposite.getChildren());
                for (Iterator<PlanComponent> iteratorDana = iteratorContainer.getIterator(); iteratorDana.hasNext();) {
                    PlanComponent pc = iteratorDana.getNext();
                    DayComposite dayComposite = (DayComposite) pc;
                    if (dayComposite.getDay() == uneseniDan) {
                        pc.showData();
                        iteratorContainer = new PlanRepository(dayComposite.getShows());
                        for (Iterator<PlanComponent> iteratorEmisija = iteratorContainer.getIterator(); iteratorEmisija.hasNext();) {
                            PlanComponent pc1 = iteratorEmisija.getNext();
                            if (!(pc1 instanceof SpecificShow)) {
                                break;
                            }
                            pc1.showData();
                        }
                    }
                }
            }

        }
    }

    public void printSecondOption(List<PlanComponent> listaPrograma) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Unesite ID programa: ");
        int uneseniIdProgram = Integer.parseInt(br.readLine());
        System.out.print("Unesite dan u tjednu(broj dana):");
        int uneseniDan = Integer.parseInt(br.readLine());
        System.out.println("\n");
        int suma = 0;
        IteratorContainer iteratorContainer = new PlanRepository(listaPrograma);
        for (Iterator<PlanComponent> iteratorPrograma = iteratorContainer.getIterator(); iteratorPrograma.hasNext();) {
            PlanComponent planComponent = iteratorPrograma.getNext();
            ProgramComposite programComposite = (ProgramComposite) planComponent;
            if (programComposite.getId() == uneseniIdProgram) {
                planComponent.showData();
                iteratorContainer = new PlanRepository(programComposite.getChildren());
                for (Iterator<PlanComponent> iteratorDana = iteratorContainer.getIterator(); iteratorDana.hasNext();) {
                    PlanComponent pc = iteratorDana.getNext();
                    if (pc instanceof DayComposite) {
                        DayComposite dayComposite = (DayComposite) pc;
                        if (dayComposite.getDay() == uneseniDan) {
                            pc = new RevenueDayDecoration(pc);
                            pc.showData();
                            iteratorContainer = new PlanRepository(dayComposite.getShows());
                            for (Iterator<PlanComponent> iteratorEmisija = iteratorContainer.getIterator(); iteratorEmisija.hasNext();) {
                                PlanComponent pc1 = iteratorEmisija.getNext();
                                if (!(pc1 instanceof SpecificShow)) {
                                    break;
                                }
                                SpecificShow specificShow = (SpecificShow) pc1;
                                if (specificShow.getShow().getShowType().getAdvertisement() == 1) {
                                    suma += specificShow.getShow().getShowType().getMaxAdvertisementDuration();
                                    pc1 = new RevenueShowDecoration(pc1);
                                    pc1.showData();
                                }
                            }
                            Visitor visitor = new ConcreteVisitor();
                            CompositeCollection cc = new CompositeCollection(dayComposite);
                            System.out.println("Suma dobro: " + suma);
                            System.out.println("Suma: " + visitor.visitComposite(cc));
                        }
                    }
                }
            }
        }
    }

    public void printThirdOption(List<PlanComponent> listaPrograma) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Unesite ID vrste emisije za ispis: ");
        int unesenaVrstaEmisije = Integer.parseInt(br.readLine());
        IteratorContainer containerZaVrstuEmisije = new ShowTypeRepository(listaPrograma, unesenaVrstaEmisije);
        for (Iterator<PlanComponent> iterator = containerZaVrstuEmisije.getIterator(); iterator.hasNext();) {
            PlanComponent vrstaEmisije = iterator.getNext();
            vrstaEmisije.showData();
        }
    }

    public void printFourthOption(List<PlanComponent> listaPrograma) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (Person person : TVHouse.getInstance().getPersons()) {
            System.out.println(person.getId() + " - " + person.getNameAndSurname());
        }
        System.out.print("Unesite ID osobe: ");
        int uneseniIdOsobe = Integer.parseInt(br.readLine());
        if (PersonHelper.getOsoba(uneseniIdOsobe) == null) {
            System.out.println("Ne postoji osoba s unesenim ID!");
            return;
        }
        for (Role role : TVHouse.getInstance().getRoles()) {
            System.out.println(role.getId() + " - " + role.getDescription());
        }
        System.out.print("Unesite ID uloge koju želite zamijeniti: ");
        int uneseniIdUlogeZaIzmjeniti = Integer.parseInt(br.readLine());
        if (RoleHelper.getUloga(uneseniIdUlogeZaIzmjeniti) == null) {
            System.out.println("Ne postoji uloga s unesenim ID!");
            return;
        }
        for (Role role : TVHouse.getInstance().getRoles()) {
            System.out.println(role.getId() + " - " + role.getDescription());
        }
        System.out.print("Unesite ID uloge koju želite dodati: ");
        int uneseniIdUlogeZaDodati = Integer.parseInt(br.readLine());
        if (RoleHelper.getUloga(uneseniIdUlogeZaDodati) == null) {
            System.out.println("Ne postoji uloga s unesenim ID!");
            return;
        }
        for (Subject subject : TVHouse.getInstance().getSubjects()) {
            PersonRole personRole = (PersonRole) subject;
            if (personRole.getPerson().getId() == uneseniIdOsobe
                    && personRole.getRole().getId() == uneseniIdUlogeZaIzmjeniti) {
                personRole.updateAll(uneseniIdUlogeZaDodati);
            }
        }
    }

    public void printFifthOption(List<PlanComponent> listaPrograma) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Unesite ID emisije za brisanje: ");
        int showID = Integer.parseInt(br.readLine());
        System.out.println("\n");
        IteratorContainer iteratorContainer = new PlanRepository(listaPrograma);
        for (Iterator<PlanComponent> iteratorPrograma = iteratorContainer.getIterator(); iteratorPrograma.hasNext();) {
            PlanComponent planComponent = iteratorPrograma.getNext();
            ProgramComposite programComposite = (ProgramComposite) planComponent;
            iteratorContainer = new PlanRepository(programComposite.getChildren());
            for (Iterator<PlanComponent> iteratorDana = iteratorContainer.getIterator(); iteratorDana.hasNext();) {
                PlanComponent pc = iteratorDana.getNext();
                if (pc instanceof DayComposite) {
                    DayComposite dayComposite = (DayComposite) pc;
                    iteratorContainer = new PlanRepository(dayComposite.getShows());
                    for (Iterator<PlanComponent> iteratorEmisija = iteratorContainer.getIterator(); iteratorEmisija.hasNext();) {
                        PlanComponent pc1 = iteratorEmisija.getNext();
                        if ((pc1 instanceof SpecificShow)) {
                            SpecificShow specificShow = (SpecificShow) pc1;
                            if (specificShow.getId() == showID) {
                                Caretaker.addMemento(listaPrograma);
                                iteratorEmisija.remove(pc1);
                            }

                        }
                    }
                }
            }

        }
    }

    public void printSixthOption() {
        Caretaker.printSavedStates();
    }

    public List<PlanComponent> printSeventhOption() throws IOException, Exception {
        Caretaker.printSavedStates();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Unesite ID spremanja na koji zelite vratiti podatke: ");
        int saveID = Integer.parseInt(br.readLine());
        return Caretaker.returnMemento(saveID);
    }

    public void printEigthOption(List<PlanComponent> listaPrograma) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Unesite ID programa: ");
        int uneseniIdProgram = Integer.parseInt(br.readLine());
        System.out.print("Unesite dan u tjednu(broj dana):");
        int uneseniDan = Integer.parseInt(br.readLine());
        int suma = 0;
        System.out.println("\n");
        IteratorContainer iteratorContainer = new PlanRepository(listaPrograma);
        for (Iterator<PlanComponent> iteratorPrograma = iteratorContainer.getIterator(); iteratorPrograma.hasNext();) {
            PlanComponent planComponent = iteratorPrograma.getNext();
            ProgramComposite programComposite = (ProgramComposite) planComponent;
            if (programComposite.getId() == uneseniIdProgram) {
                programComposite.showData();
                iteratorContainer = new PlanRepository(programComposite.getChildren());
                for (Iterator<PlanComponent> iteratorDana = iteratorContainer.getIterator(); iteratorDana.hasNext();) {
                    PlanComponent pc = iteratorDana.getNext();
                    DayComposite dayComposite = (DayComposite) pc;
                    if (dayComposite.getDay() == uneseniDan) {
                        dayComposite.showData();
                        iteratorContainer = new PlanRepository(dayComposite.getShows());
                        for (Iterator<PlanComponent> iteratorEmisija = iteratorContainer.getIterator(); iteratorEmisija.hasNext();) {
                            PlanComponent pc1 = iteratorEmisija.getNext();
                            if (!(pc1 instanceof SpecificShow)) {
                                break;
                            }
                            SpecificShow specificShow = (SpecificShow) pc1;
                            if (specificShow.getShow().getShowType().getAdvertisement() == 1) {
                                pc1 = new RevenueShowDecoration(pc1);
                                pc1.showData();
                                suma += specificShow.getShow().getShowType().getMaxAdvertisementDuration();
                            }
                        }
                    }
                }
            }
        }
        Handler highRevenue = new HighRevenueIncomeHandler();
        Handler nomalRevenue = new NormalRevenueHandler();
        Handler lowRevenue = new LowRevenueIncomeHandler();
        lowRevenue.setNext(nomalRevenue);
        nomalRevenue.setNext(highRevenue);
        lowRevenue.handle(suma);
        System.out.println("\n");
    }
}
