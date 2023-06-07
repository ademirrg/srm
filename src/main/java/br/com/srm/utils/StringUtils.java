package br.com.srm.utils;

public class StringUtils {

    public static String removeMask(String string) {
        return string == null ? null : string.replaceAll("\\D", "");
    }
}
