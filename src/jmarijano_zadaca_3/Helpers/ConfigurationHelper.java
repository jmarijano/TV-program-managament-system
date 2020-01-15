package jmarijano_zadaca_3.Helpers;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;
import jmarijano_zadaca_3.FactoryMethod.ConcreteCreator.PersonFileCreator;
import jmarijano_zadaca_3.FactoryMethod.ConcreteCreator.ProgramFileCreator;
import jmarijano_zadaca_3.FactoryMethod.ConcreteCreator.RoleFileCreator;
import jmarijano_zadaca_3.FactoryMethod.ConcreteCreator.ShowFileCreator;
import jmarijano_zadaca_3.FactoryMethod.ConcreteCreator.ShowTypeFileCreator;
import jmarijano_zadaca_3.FactoryMethod.ConcreteCreator.SpecificShowFileCreator;
import jmarijano_zadaca_3.FactoryMethod.Creator.FileCreator;
import jmarijano_zadaca_3.FactoryMethod.Product.FileProduct;
import jmarijano_zadaca_3.Singleton.TVHouse;
import jmarijano_zadaca_3.org.Person;
import jmarijano_zadaca_3.org.Program;
import jmarijano_zadaca_3.org.Role;
import jmarijano_zadaca_3.org.Show;
import jmarijano_zadaca_3.org.ShowType;
import jmarijano_zadaca_3.org.SpecificShow;

public class ConfigurationHelper {

    public static boolean hasValidNumberOfArguments(String[] args) {
        return args.length == 10;
    }

    public static boolean hasValidFlags(List<String> argumenti) {
        return !(!argumenti.contains("-t") || !argumenti.contains("-e")
                || !argumenti.contains("-o") || !argumenti.contains("-u")
                || !argumenti.contains("-v"));
    }

    public static void setupPersons(List<String> argumenti) {
        int indexO = argumenti.indexOf("-o");
        String datOsobe = argumenti.get(indexO + 1);
        FileCreator osobaDatotekaCreator = new PersonFileCreator();
        FileProduct osobaProduct = osobaDatotekaCreator.makeProduct();
        TVHouse.getInstance().setPersons((List<Person>) osobaProduct.getConcreteProducts(datOsobe));
    }

    public static void setupRoles(List<String> argumenti) {
        int index = argumenti.indexOf("-u");
        String roles = argumenti.get(index + 1);
        FileCreator roleFileCreator = new RoleFileCreator();
        FileProduct roleProduct = roleFileCreator.makeProduct();
        TVHouse.getInstance().setRoles((List<Role>) roleProduct.getConcreteProducts(roles));
    }

    public static void setupShowTypes(List<String> argumenti) {
        int index = argumenti.indexOf("-v");
        String showTypes = argumenti.get(index + 1);
        FileCreator showTypeCreator = new ShowTypeFileCreator();
        FileProduct showTypeProduct = showTypeCreator.makeProduct();
        TVHouse.getInstance().setShowTypes((List<ShowType>) showTypeProduct.getConcreteProducts(showTypes));
    }

    public static void setupShows(List<String> argumenti) {
        int index = argumenti.indexOf("-e");
        String shows = argumenti.get(index + 1);
        FileCreator showCreator = new ShowFileCreator();
        FileProduct showProduct = showCreator.makeProduct();
        TVHouse.getInstance().setShows((List<Show>) showProduct.getConcreteProducts(shows));

    }

    public static void setupPrograms(List<String> argumenti) {
        int index = argumenti.indexOf("-t");
        String programs = argumenti.get(index + 1);
        FileCreator programCreator = new ProgramFileCreator();
        FileProduct programProduct = programCreator.makeProduct();
        TVHouse.getInstance().setPrograms((List<Program>) programProduct.getConcreteProducts(programs));
        TVHouse.getInstance().setFilePath(programs);
    }

    public static void setupSpecificShows() {
        FileCreator programEmisijaCreator = new SpecificShowFileCreator();
        FileProduct programEmisijaProduct = programEmisijaCreator.makeProduct();
        for (Program program : TVHouse.getInstance().getPrograms()) {
            String nazivKuce = TVHouse.getInstance().getFilePath();
            String pattern = Pattern.quote(System.getProperty("file.separator"));
            String[] putanja = nazivKuce.split(pattern);
            String nazivPrograma = "";
            for (int i = 0; i < putanja.length - 1; i++) {
                nazivPrograma += putanja[i] + File.separator;
            }
            nazivPrograma += program.getFileName();
            int index = 0;
            List<SpecificShow> listaEmisija = (List<SpecificShow>) programEmisijaProduct.getConcreteProducts(nazivPrograma);
            program.setSpecificShows(listaEmisija);
        }
    }
}
