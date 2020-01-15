package jmarijano_zadaca_3.FactoryMethod.ConcreteProduct;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import jmarijano_zadaca_3.org.Person;
import jmarijano_zadaca_3.FactoryMethod.Product.FileProduct;

public class PersonFileProduct implements FileProduct {

    @Override
    public List<Person> getConcreteProducts(String fileName) {
        List<Person> output = new ArrayList<>();
        String line = "";
        String mainSeparator = ";";
        int iteration = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))) {
            while ((line = br.readLine()) != null) {
                if (iteration == 0) {
                    iteration++;
                    continue;
                }
                String[] persons = line.split(mainSeparator, -1);
                Person person = new Person();
                try {
                    person.setId(Integer.parseInt(persons[0].trim()));
                    String forNameAndSurname = persons[1].trim();
                    if (forNameAndSurname == null || forNameAndSurname.isEmpty()) {
                        throw new Exception();
                    }
                    person.setNameAndSurname(persons[1].trim());
                    output.add(person);
                } catch (Exception e) {
                    System.err.println("Zapis osobe:" + line + ": je kriv!");
                }
            }
        } catch (Exception e) {
            System.out.println("Dogodila se greška prilikom čitanja datoteke osoba");
            System.exit(-1);
        }
        return output;
    }
}
