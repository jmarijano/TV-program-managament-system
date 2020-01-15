package jmarijano_zadaca_3.Helpers;

import java.util.List;
import jmarijano_zadaca_3.Singleton.TVHouse;
import jmarijano_zadaca_3.org.Show;

public class ShowHelper {

    public static Show getEmisija(int id) {
        List<Show> kae=TVHouse.getInstance().getShows();
        for (Show emisija : TVHouse.getInstance().getShows()) {
            if (emisija.getId() == id) {
                return emisija;
            }
        }
        return null;
    }

    public static boolean isNull(Show emisija) {
        if (emisija == null) {
            return true;
        }
        return false;
    }
}
