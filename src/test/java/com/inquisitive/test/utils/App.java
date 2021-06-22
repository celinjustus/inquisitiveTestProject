package com.inquisitive.test.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {
    private static Properties prop = new Properties();
    private static Boolean isLoaded = false;

    public static Properties loadProperties() {
        InputStream input = null;
        try {
            input = new FileInputStream("properties/config.properties");
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {

                }
            }
        }
        isLoaded = true;
        return prop;
    }

    public static Properties getProperties() {
        if (isLoaded) {
            return prop;

        } else {
            loadProperties();
            return prop;
        }
    }

    public static String getProperty(String key){
        return getProperties().getProperty(key);
    }
}
