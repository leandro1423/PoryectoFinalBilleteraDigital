// ValidacionUtils.java
package co.edu.uniquindio.poo.billeteradigital.utils;

import co.edu.uniquindio.poo.billeteradigital.interfaces.IValidacionesUtils;

public class ValidacionUtils implements IValidacionesUtils {

    private ValidacionUtils() {
        // Evita instancias de esta clase de utilidades
    }

    @Override
    public boolean esNuloOVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    @Override
    public boolean camposSonValidos(String... campos) {
        for (String campo : campos) {
            if (esNuloOVacio(campo)) {
                return false;
            }
        }
        return true;
    }

    public static String generarId(int cantidadActual) {
        return String.format("%02d", cantidadActual + 1);
    }
}
