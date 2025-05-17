// ValidacionUtil.java
package co.edu.uniquindio.poo.billeteradigital.utils;

public class ValidacionUtils {

    public static boolean esNuloOVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public static boolean camposSonValidos(String... campos) {
        for (String campo : campos) {
            if (esNuloOVacio(campo)) {
                return false;
            }
        }
        return true;
    }
}
