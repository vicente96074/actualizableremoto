/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softquark.gt.actualizableremoto.main;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class UpdateChecker {
    
    private final static String UPDATE_URL = "https://raw.githubusercontent.com/vicente96074/actualizableremoto/main/update.json";
    
    public static boolean isUpdateAvailable(String currentVersion) throws IOException {
        String latestVersion = getLatestVersion();
        return latestVersion != null && !latestVersion.equals(currentVersion);
    }
    
    private static String getLatestVersion() throws IOException {
        URL url = new URL(UPDATE_URL);
        try (InputStream stream = url.openStream()) {
            Scanner scanner = new Scanner(stream);
            String content = scanner.useDelimiter("\\A").next();
            return content.split("\"version\"\\s*:\\s*\"")[1].split("\"")[0];
        }
    }
}
