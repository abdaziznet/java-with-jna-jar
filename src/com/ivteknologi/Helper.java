package com.ivteknologi;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Helper {

    private static final String STRING_EMPTY = "";

    public static void infobox(String message, String titleBar) {
        JOptionPane.showMessageDialog(null, message, titleBar, JOptionPane.INFORMATION_MESSAGE);

    }

    public static String getLocationLib() {
        String path = STRING_EMPTY;
        try {
            Properties properties = new Properties();

            String arch = getOSArchitecture();
            FileInputStream fileInputStream = new FileInputStream(arch);
            properties.loadFromXML(fileInputStream);
            path = properties.getProperty("jdbc.finger.btn.web.path");
        } catch (FileNotFoundException ex) {
            infobox(ex.getMessage(), "ERROR");
        } catch (IOException ex) {
            infobox(ex.getMessage(), "ERROR");
        }
        return path;
    }

    public static String getOSArchitecture(){
        String arch = System.getenv("PROCESSOR_ARCHITECTURE");
        String wow64Arch = System.getenv("PROCESSOR_ARCHITEW6432");

        String realArch = arch.endsWith("64") || wow64Arch != null && wow64Arch.endsWith("64") ? "64" : "32";

        if (realArch == "64"){
            return "C:\\Program Files (x86)\\Ivatama Teknologi\\Fingerprint\\Config\\bdsweb_properties.xml";
        }else{
            return "C:\\Program Files\\Ivatama Teknologi\\Fingerprint\\Config\\bdsweb_properties.xml";
        }

    }
}
