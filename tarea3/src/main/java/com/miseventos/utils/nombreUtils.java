package com.miseventos.utils;

public class nombreUtils {

    public static String normalizarNombre(String texto) {
        if (texto == null) return "";
        String limpio = texto.toLowerCase()
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("ñ", "n")
                .replaceAll("[^a-z0-9]", "");
        return limpio;
    }
}

