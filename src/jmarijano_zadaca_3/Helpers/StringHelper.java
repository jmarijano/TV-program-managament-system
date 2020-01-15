/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmarijano_zadaca_3.Helpers;

/**
 *
 * @author jocin
 */
public class StringHelper {

    public static String getCenteredString(String string, int brojZnakova) {

        int length = string.length();
        int padding = (brojZnakova - length) / 2;//decide left and right padding
        if (padding <= 0) {
            return string;
        }

        String empty = "", hash = "$";//hash is used as a place holder

        // extra character in case of String with even length
        int extra = (length % 2 == 0) ? 1 : 0;

        String leftPadding = "%" + padding + "s";
        String rightPadding = "%" + (padding - extra) + "s";

        String strFormat = leftPadding + "%s" + rightPadding;
        String formattedString = String.format(strFormat, empty, hash, empty);
        String paddedString = formattedString.replace(' ', ' ').replace(hash, string);
        return paddedString;
    }
}
