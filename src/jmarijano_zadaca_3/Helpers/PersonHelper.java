package jmarijano_zadaca_3.Helpers;

import jmarijano_zadaca_3.Singleton.TVHouse;
import jmarijano_zadaca_3.org.Person;

public class PersonHelper {

    public static Person getOsoba(int id) {
        for (Person osoba : TVHouse.getInstance().getPersons()) {
            if (osoba.getId() == id) {
                return osoba;
            }
        }
        return null;
    }

    public static boolean isNull(Person osoba) {
        return osoba == null;
    }

}
