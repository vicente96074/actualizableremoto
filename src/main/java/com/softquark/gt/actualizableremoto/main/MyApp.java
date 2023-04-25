/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softquark.gt.actualizableremoto.main;

import java.io.IOException;

public class MyApp {

    private final static String CURRENT_VERSION = "1.0.0";

    public static void main(String[] args) {
        try {
            if (UpdateChecker.isUpdateAvailable(CURRENT_VERSION)) {
                System.out.println("¡Hay una actualización disponible!");
                // Mostrar un cuadro de diálogo al usuario para preguntar si desea actualizar
            } else {
                System.out.println("¡Tu aplicación está actualizada!");
            }
        } catch (IOException e) {
            System.err.println("Error al verificar actualizaciones: " + e.getMessage());
        }
    }
}
