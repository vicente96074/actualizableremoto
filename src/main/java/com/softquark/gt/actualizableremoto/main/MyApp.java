/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softquark.gt.actualizableremoto.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.IOException;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyApp {

    private final static String CURRENT_VERSION = "1.0.3";
    public final static String DOWNLOAD_URL = "https://raw.githubusercontent.com/vicente96074/actualizableremoto/main/releases/download/v1.0.0/ActualizableRemoto.jar";

    public static void main(String[] args) {

        String estado = "";

        try {
            if (UpdateChecker.isUpdateAvailable(CURRENT_VERSION)) {
                System.out.println("¡Hay una actualización disponible!");
                estado = "¡Hay una actualización disponible!";
                boolean update = UpdateChecker.downloadAndReplaceJar(DOWNLOAD_URL);

                System.out.println("Todo bien: " + update);

                // Mostrar un cuadro de diálogo al usuario para preguntar si desea actualizar
            } else {
                System.out.println("¡Tu aplicación está actualizada!");
                estado = "¡Tu aplicación está actualizada!";
            }
        } catch (IOException e) {
            System.err.println("Error al verificar actualizaciones: " + e.getMessage());
            estado = "Error al verificar actualizaciones: " + e.getMessage();
        }

        new Ventana(estado).setVisible(true);

    }
}

class Ventana extends JFrame {

    public Ventana(String estado) throws HeadlessException {
        this.estado = estado;
        initComponents();
    }

    private void initComponents() {
        this.setSize(new Dimension(300, 300));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.setBackground(new Color(60, 63, 65));

        panel = new JPanel();
        jLabel = new JLabel();
        jButton = new JButton();

        panel.setBackground(new Color(60, 63, 65));

        jLabel.setText(estado);
        jLabel.setHorizontalTextPosition(0);

        GroupLayout layoutPanel = new GroupLayout(panel);
        panel.setLayout(layoutPanel);
        layoutPanel.setHorizontalGroup(layoutPanel.createSequentialGroup()
                .addGap(5, 5, Short.MAX_VALUE)
                .addGroup(layoutPanel.createParallelGroup()
                        .addComponent(jLabel, 200, 200, 200)
                        .addComponent(jButton, 200, 200, 200)
                )
                .addGap(5, 5, Short.MAX_VALUE)
        );

        layoutPanel.setVerticalGroup(layoutPanel.createSequentialGroup()
                .addGap(5, 5, Short.MAX_VALUE)
                .addComponent(jLabel, 30, 30, 30)
                .addGap(5, 5, 5)
                .addComponent(jButton, 30, 30, 30)
                .addGap(5, 5, Short.MAX_VALUE)
        );

        this.add(panel);

    }

    private String estado;

    private JPanel panel;
    private JLabel jLabel;
    private JButton jButton;

}
