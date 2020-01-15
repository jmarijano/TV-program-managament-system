package jmarijano_zadaca_3.FactoryMethod.ConcreteProduct;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import jmarijano_zadaca_3.org.Role;
import jmarijano_zadaca_3.FactoryMethod.Product.FileProduct;

public class RoleFileProduct implements FileProduct {

    @Override
    public List<Role> getConcreteProducts(String fileName) {
        List<Role> output = new ArrayList<>();
        String line = "";
        String separatorGlavni = ";";
        int iteracija = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))) {
            while ((line = br.readLine()) != null) {
                if (iteracija == 0) {
                    iteracija++;
                    continue;
                }
                String[] uloge = line.split(separatorGlavni, -1);
                Role uloga = new Role();
                try {
                    if (uloge.length != 2) {
                        throw new Exception();
                    }
                    uloga.setId(Integer.parseInt(uloge[0].trim()));
                    String zaOpis = uloge[1].trim();
                    if (zaOpis == null || zaOpis.isEmpty()) {
                        throw new Exception();
                    }
                    uloga.setDescription(zaOpis);

                    output.add(uloga);
                } catch (Exception e) {
                    System.err.println("Zapis uloge:" + line + ": je kriv!");
                }
            }
        } catch (Exception e) {
            System.exit(-1);
        }
        return output;
    }
}
