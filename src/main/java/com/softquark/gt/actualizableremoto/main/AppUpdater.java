/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softquark.gt.actualizableremoto.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppUpdater {

    private static final String UPDATE_CONFIG_URL = "https://github.com/vicente96074/actualizableremoto/tree/main/releases/latest/update/config.json";
    private static final String APP_JAR_URL = "https://github.com/vicente96074/actualizableremoto/tree/main/releases/latest/update/app.jar";

    private static final String APP_JAR_FILE_NAME = "app.jar";
    private static final String APP_VERSION_FILE_NAME = "version.txt";
    private static final String APP_VERSION_PATTERN = "^\\d+\\.\\d+\\.\\d+$";

    private static final Path APP_DIR = Paths.get(System.getProperty("user.dir"));

    public static void main(String[] args) {
        try {
            String currentVersion = getCurrentVersion();
            String latestVersion = getLatestVersion();
            if (latestVersion != null && !latestVersion.equals(currentVersion)) {
                downloadUpdate();
                restartApp();

                System.out.println("¡Si hay versión nueva!");

            } else {
                System.out.println("No updates available.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentVersion() throws IOException {
        Path versionFile = APP_DIR.resolve(APP_VERSION_FILE_NAME);
        if (Files.exists(versionFile)) {
            return Files.readString(versionFile).trim();
        } else {
            return "0.0.0";
        }
    }

    private static String getLatestVersion() throws IOException {
        URL updateConfigUrl = new URL(UPDATE_CONFIG_URL);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(updateConfigUrl.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.matches(APP_VERSION_PATTERN)) {
                    return line;
                }
            }
        }
        return null;
    }

    private static void downloadUpdate() throws IOException {
        URL appJarUrl = new URL(APP_JAR_URL);
        Path appJarFile = APP_DIR.resolve(APP_JAR_FILE_NAME);
        Files.copy(appJarUrl.openStream(), appJarFile);
        Path versionFile = APP_DIR.resolve(APP_VERSION_FILE_NAME);
        Files.write(versionFile, getLatestVersion().getBytes());
    }

    private static void restartApp() throws IOException {
        String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        String classPath = System.getProperty("java.class.path");
        String mainClass = AppUpdater.class.getCanonicalName();
        ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classPath, mainClass);
        builder.start();
        System.exit(0);
    }
}
