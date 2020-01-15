package jmarijano_zadaca_3.Helpers;

import jmarijano_zadaca_3.Singleton.TVHouse;
import jmarijano_zadaca_3.org.Role;

public class RoleHelper {

    public static Role getUloga(int id) {
        for (Role uloga : TVHouse.getInstance().getRoles()) {
            if (uloga.getId() == id) {
                return uloga;
            }
        }
        return null;
    }

    public static boolean isNull(Role uloga) {
        return uloga == null;
    }
}
