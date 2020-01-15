package jmarijano_zadaca_3.Helpers;

import jmarijano_zadaca_3.Singleton.TVHouse;
import jmarijano_zadaca_3.org.ShowType;

public class ShowTypeHelper {

    public static ShowType getVrstaEmisije(int id) {
        for (ShowType vrstaEmisije : TVHouse.getInstance().getShowTypes()) {
            if (vrstaEmisije.getId() == id) {
                return vrstaEmisije;
            }
        }
        return null;
    }

}
