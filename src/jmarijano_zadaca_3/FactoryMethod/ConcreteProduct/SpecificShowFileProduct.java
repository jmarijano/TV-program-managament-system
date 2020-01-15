package jmarijano_zadaca_3.FactoryMethod.ConcreteProduct;

import jmarijano_zadaca_3.org.PersonRole;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import jmarijano_zadaca_3.Helpers.ShowHelper;
import jmarijano_zadaca_3.Helpers.PersonHelper;
import jmarijano_zadaca_3.Helpers.RoleHelper;
import jmarijano_zadaca_3.org.Person;
import jmarijano_zadaca_3.org.SpecificShow;
import jmarijano_zadaca_3.org.Role;
import jmarijano_zadaca_3.FactoryMethod.Product.FileProduct;
import jmarijano_zadaca_3.Observer.Subject;
import jmarijano_zadaca_3.Singleton.TVHouse;

public class SpecificShowFileProduct implements FileProduct {

    public void provjeriDane(int pocetak, int kraj) throws Exception {
        if (pocetak < 1 || pocetak > 7 || kraj < 1 || kraj > 7) {
            throw new Exception();
        }
    }

    public List<PersonRole> dodajOsobe(String[] osobaUlogaZapisi) throws Exception {
        List<PersonRole> osobaUlogaHelpers = new ArrayList<>();
        for (String string : osobaUlogaZapisi) {
            string = string.trim();
            String[] svakiPosebno = string.split("-");
            int zaIdOsobe = Integer.parseInt(svakiPosebno[0].trim());
            int zaIdUloge = Integer.parseInt(svakiPosebno[1].trim());
            Person osoba = PersonHelper.getOsoba(zaIdOsobe);
            Role uloga = RoleHelper.getUloga(zaIdUloge);
            if (PersonHelper.isNull(osoba)
                    || RoleHelper.isNull(uloga)) {
                throw new Exception();
            }
            PersonRole osobaUlogaHelper = new PersonRole();
            osobaUlogaHelper.setPerson(osoba);
            osobaUlogaHelper.setRole(uloga);
            osobaUlogaHelpers.add(osobaUlogaHelper);
            TVHouse.getInstance().getSubjects().add(osobaUlogaHelper);
            
        }
        return osobaUlogaHelpers;
    }

    public SpecificShow postaviVremena(String zaPocetak, SpecificShow programEmisija) {
        String[] zaFormatPocetka = zaPocetak.split(":");
        int pocetakSati = Integer.parseInt(zaFormatPocetka[0].trim());
        int pocetakMinute = Integer.parseInt(zaFormatPocetka[1].trim());
        int pocetakSekunde = 0;
        if (zaPocetak.length() == 3) {
            pocetakSekunde = Integer.parseInt(zaFormatPocetka[2].trim());
        }
        LocalTime pocetak = LocalTime.of(pocetakSati, pocetakMinute, pocetakSekunde);
        programEmisija.setStartTime(pocetak);
        programEmisija.setEndTime(programEmisija.getStartTime().
                plusMinutes(programEmisija.getShow().getDuration()));
        return programEmisija;
    }

    @Override
    public List<?> getConcreteProducts(String fileName) {
        List<SpecificShow> output = new ArrayList<>();
        String line = "";
        String separatorGlavni = ";";
        int iteracija = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))) {
            while ((line = br.readLine()) != null) {
                if (iteracija == 0) {
                    iteracija++;
                    continue;
                }
                String programEmisije[] = line.split(separatorGlavni, -1);
                try {
                    SpecificShow programEmisija = new SpecificShow();
                    int zaEmisiju = Integer.parseInt(programEmisije[0].trim());
                    switch (programEmisije.length) {
                        case 4:
                            programEmisija.setShow(ShowHelper.getEmisija(zaEmisiju));
                            if (ShowHelper.isNull(programEmisija.getShow())) {
                                throw new Exception(); 
                           }
                            for (Subject subject : programEmisija.getShow().getPersonRoles()) {
                                programEmisija.subscribe(subject);
                            }
                            String sadrzajDana = programEmisije[1].trim();
                            String[] daniPoCrtici = programEmisije[1].split("-");
                            String[] osobaUlogaZapisi = programEmisije[3].split(",");
                            String[] daniPoZarezu = programEmisije[1].split(",");
                            String zaPocetak = programEmisije[2].trim();
                            if (zaPocetak != null && !zaPocetak.isEmpty()) {
                                programEmisija = postaviVremena(zaPocetak, programEmisija);
                            }
                            if (osobaUlogaZapisi != null && !programEmisije[3].trim().isEmpty()) {
                                for (PersonRole personRole : dodajOsobe(osobaUlogaZapisi)) {
                                    programEmisija.subscribe(personRole);
                                }

                            }
                            if (daniPoCrtici.length == 2) {
                                int pocetak = Integer.parseInt(daniPoCrtici[0].trim());
                                int kraj = Integer.parseInt(daniPoCrtici[1].trim());
                                provjeriDane(pocetak, kraj);
                                for (int i = pocetak; i <= kraj; i++) {
                                    output.add(new SpecificShow(0,i, programEmisija.getShow(),
                                            programEmisija.getEndTime(), programEmisija.getStartTime(),
                                            programEmisija.getPersonRoles()));
                                }
                            } else if (daniPoZarezu.length >= 2) {
                                for (String string : daniPoZarezu) {
                                    output.add(new SpecificShow(0,Integer.parseInt(string.trim()),
                                            programEmisija.getShow(), programEmisija.getEndTime(),
                                            programEmisija.getStartTime(),
                                            programEmisija.getPersonRoles()));
                                }
                            } else if (daniPoCrtici.length == 1 && daniPoZarezu.length == 1
                                    && !sadrzajDana.trim().isEmpty()) {
                                output.add(new SpecificShow(0,Integer.parseInt(sadrzajDana),
                                        programEmisija.getShow(), programEmisija.getEndTime(),
                                        programEmisija.getStartTime(),
                                        programEmisija.getPersonRoles()));

                            }
                            break;
                        case 3:
                            programEmisija.setShow(ShowHelper.getEmisija(zaEmisiju));
                            if (ShowHelper.isNull(programEmisija.getShow())) {
                                throw new Exception();
                            }
                            for (Subject subject : programEmisija.getShow().getPersonRoles()) {
                                programEmisija.subscribe(subject);
                            }
                            String[] osobaUlogaZapisi1 = programEmisije[2].split(",");
                            String[] daniPoZarezu1 = programEmisije[1].split(",");
                            if (osobaUlogaZapisi1 != null && !programEmisije[2].trim().isEmpty()) {
                                for (PersonRole personRole : dodajOsobe(osobaUlogaZapisi1)) {
                                    programEmisija.subscribe(personRole);
                                }
                            }
                            if (daniPoZarezu1.length >= 2) {
                                for (String string : daniPoZarezu1) {
                                    output.add(new SpecificShow(0,Integer.parseInt(string.trim()),
                                            programEmisija.getShow(), programEmisija.getEndTime(),
                                            programEmisija.getStartTime(),
                                            programEmisija.getPersonRoles()));
                                }
                            }
                            break;
                        default:
                            throw new Exception();
                    }
                    iteracija++;
                } catch (Exception e) {
                    System.err.println("Zapis emisije programa:" + line + ": je kriv!");
                }
            }
        } catch (Exception e) {
            System.out.println("Dogodila se greška prilikom"
                    + " čitanja datoteke programa i njegovih emisija");
            System.exit(-1);
        }
        return output;
    }
}
