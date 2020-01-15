package jmarijano_zadaca_3.FactoryMethod.ConcreteProduct;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import jmarijano_zadaca_3.org.ShowType;
import jmarijano_zadaca_3.FactoryMethod.Product.FileProduct;

public class ShowTypeFileProduct implements FileProduct {

    @Override
    public List<ShowType> getConcreteProducts(String fileName) {
        List<ShowType> output = new ArrayList<>();
        String line = "";
        String separatorGlavni = ";";
        String separatorOsobaUloga = ",";
        int iteracija = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))) {
            while ((line = br.readLine()) != null) {
                if (iteracija == 0) {
                    iteracija++;
                    continue;
                }
                try {
                    String[] zapisDatoteke = line.split(separatorGlavni, -1);
                    if (zapisDatoteke.length != 4) {
                        throw new Exception();
                    }
                    int zaId = Integer.parseInt(zapisDatoteke[0].trim());
                    String zaNaziv = zapisDatoteke[1].trim();
                    int zaReklamu = Integer.parseInt(zapisDatoteke[2].trim());
                    int zaMaxTrajanjeReklame = Integer.parseInt(zapisDatoteke[3].trim());
                    if (zapisDatoteke.length != 4 || (zaReklamu < 0 || zaReklamu > 1)
                            || zaMaxTrajanjeReklame < 0 || zaNaziv == null || zaNaziv.isEmpty()) {
                        throw new Exception();
                    }
                    ShowType vrstaEmisije = new ShowType();
                    vrstaEmisije.setId(zaId);
                    vrstaEmisije.setName(zaNaziv);
                    vrstaEmisije.setAdvertisement(zaReklamu);
                    vrstaEmisije.setMaxAdvertisementDuration(zaMaxTrajanjeReklame);
                    output.add(vrstaEmisije);
                } catch (Exception e) {
                    System.err.println("Zapis datoteke vrsta emisija:" + line + ": je kriv!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

}
