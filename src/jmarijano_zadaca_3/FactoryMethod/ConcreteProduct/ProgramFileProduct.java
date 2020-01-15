package jmarijano_zadaca_3.FactoryMethod.ConcreteProduct;


import jmarijano_zadaca_3.org.Program;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import jmarijano_zadaca_3.FactoryMethod.Product.FileProduct;

public class ProgramFileProduct implements FileProduct {

    @Override
    public List<Program> getConcreteProducts(String fileName) {
        List<Program> output = new ArrayList<>();
        String line = "";
        String separatorGlavni = ";";
        int iteracija = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))) {
            while ((line = br.readLine()) != null) {
                if (iteracija == 0) {
                    iteracija++;
                    continue;
                }
                String[] programi = line.split(separatorGlavni, -1);
                Program program = new Program();
                try {
                    provjeriBrojAtributa(programi);
                    String[] stringZaPocetak = programi[2].trim().split(":");
                    int sekundePocetak = postaviSekunde(programi);
                    int satiPocetak = Integer.parseInt(stringZaPocetak[0]);
                    int minutePocetak = Integer.parseInt(stringZaPocetak[1]);

                    LocalTime zaPocetak = LocalTime.of(satiPocetak, minutePocetak, sekundePocetak);
                    LocalTime zaKraj = postaviKraj(programi);
                    if (zaKraj == null) {
                        zaKraj = LocalTime.parse("23:59");
                    }
                    postaviKraj(programi);
                    provjeriVrijeme(zaPocetak, zaKraj);
                    program.setId(Integer.parseInt(programi[0].trim()));
                    String zaNaziv = programi[1].trim();
                    provjeriNaziv(zaNaziv);
                    program.setName(programi[1].trim());
                    program.setStartTime(zaPocetak);
                    program.setEndTime(zaKraj);
                    String zaNazivDatoteke = programi[4].trim();
                    provjeriNaziv(zaNazivDatoteke);
                    program.setFileName(zaNazivDatoteke);
                    System.out.println(program.getEndTime());
                    output.add(program);
                } catch (Exception e) {
                    System.err.println("Zapis programa:" + line + ": je kriv!");
                }
            }
        } catch (IOException e) {
            System.out.println("Dogodila se greška prilikom čitanja datoteke programa");
        }
        return output;
    }

    public void provjeriNaziv(String zaNaziv) throws Exception {
        if (zaNaziv == null || zaNaziv.isEmpty()) {
            throw new Exception();
        }
    }

    public void provjeriBrojAtributa(String[] programi) throws Exception {
        if (programi.length != 5) {
            throw new Exception();
        }
    }

    public int postaviSekunde(String[] polje) {
        if (polje.length == 3) {
            String sekundeS = polje[2];
            if (sekundeS != null && !sekundeS.isEmpty()) {
                return Integer.parseInt(sekundeS);
            }
        }
        return 0;
    }

    public void provjeriVrijeme(LocalTime pocetak, LocalTime kraj) throws Exception {
        if (kraj.isBefore(pocetak)) {
            throw new Exception();
        }
    }

    public LocalTime postaviKraj(String[] programi) {
        if (!programi[3].trim().isEmpty()) {
            int sekundeKraj = 0;
            String[] stringZaKraj = programi[3].trim().split(":");
            if (stringZaKraj.length == 3) {
                String sekundeZaKrajS = stringZaKraj[2];
                if (sekundeZaKrajS != null && !sekundeZaKrajS.isEmpty()) {
                    sekundeKraj = Integer.parseInt(sekundeZaKrajS);
                }
            }
            int satiKraj = Integer.parseInt(stringZaKraj[0].trim());
            int minuteKraj = Integer.parseInt(stringZaKraj[1].trim());
            LocalTime zaKraj;
            return zaKraj = LocalTime.of(satiKraj, minuteKraj, sekundeKraj);
        }
        return null;
    }
}
