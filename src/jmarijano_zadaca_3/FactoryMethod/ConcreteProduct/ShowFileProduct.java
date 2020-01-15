package jmarijano_zadaca_3.FactoryMethod.ConcreteProduct;

import jmarijano_zadaca_3.org.PersonRole;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import jmarijano_zadaca_3.Helpers.ShowHelper;
import jmarijano_zadaca_3.Helpers.PersonHelper;
import jmarijano_zadaca_3.Helpers.RoleHelper;
import jmarijano_zadaca_3.Helpers.ShowTypeHelper;
import jmarijano_zadaca_3.org.Show;
import jmarijano_zadaca_3.FactoryMethod.Product.FileProduct;
import jmarijano_zadaca_3.Singleton.TVHouse;

public class ShowFileProduct implements FileProduct {

    @Override
    public List<Show> getConcreteProducts(String fileName) {
        List<Show> output = new ArrayList<>();
        String line = "";
        String separatorGlavni = ";";
        int iteracija = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))) {
            while ((line = br.readLine()) != null) {
                if (iteracija == 0) {
                    iteracija++;
                    continue;
                }
                try {
                    List<PersonRole> osobaUlogaHelpers = new ArrayList<>();
                    String[] emisije = line.split(separatorGlavni, -1);
                    String osobeUloge = emisije[4].trim();
                    Show emisija = new Show();
                    emisija.setId(Integer.parseInt(emisije[0].trim()));
                    emisija.setShowType(ShowTypeHelper.getVrstaEmisije(Integer.
                            parseInt(emisije[2].trim())));
                    if (ShowHelper.isNull(emisija)) {
                        throw new Exception();
                    }
                    emisija.setName(emisije[1].trim());
                    int trajanje = Integer.parseInt(emisije[3].trim());
                    provjeriTrajanje(trajanje);
                    emisija.setDuration(trajanje);
                    if (osobeUloge != null && !osobeUloge.isEmpty()) {
                        String[] zadnje = osobeUloge.split(",");
                        for (int i = 0; i < zadnje.length; i++) {
                            String[] konacnoZadnje = zadnje[i].split("-");
                            PersonRole osobaUlogaHelper = new PersonRole();
                            int zaIdOsobe = Integer.parseInt(konacnoZadnje[0].trim());
                            int zaIdUloge = Integer.parseInt(konacnoZadnje[1].trim());
                            osobaUlogaHelper.setPerson(PersonHelper.getOsoba(zaIdOsobe));
                            osobaUlogaHelper.setRole(RoleHelper.getUloga(zaIdUloge));
                            if (RoleHelper.isNull(osobaUlogaHelper.getRole())
                                    || PersonHelper.isNull(osobaUlogaHelper.getPerson())) {
                                throw new Exception();
                            }
                            osobaUlogaHelpers.add(osobaUlogaHelper);
                            TVHouse.getInstance().getSubjects().add(osobaUlogaHelper);
                            emisija.setPersonRoles(osobaUlogaHelpers);
                        }
                    }
                    output.add(emisija);
                } catch (Exception e) {
                    System.err.println("Zapis emisije:" + line + ": je kriv!");
                }
            }
        } catch (IOException e) {
            System.out.println("Dogodila se greška prilikom učitanja datoteke emisije");
            System.exit(1);
        }
        return output;
    }

    public void provjeriTrajanje(int trajanje) throws Exception {
        if (trajanje <= 0) {
            throw new Exception();
        }
    }
}
