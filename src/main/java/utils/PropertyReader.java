package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by yuraku on 7/3/16.
 */
public class PropertyReader {

    private static final String CONFIG_FILE_PATH = "src/main/resources/configuration.properties";

    public static Properties loadProperty() {

        InputStream input = null;
        Properties properties = new Properties();

        try {
            input = new FileInputStream(CONFIG_FILE_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
