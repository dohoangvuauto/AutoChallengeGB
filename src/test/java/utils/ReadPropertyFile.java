package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
    private static Properties prop;
    public static Properties load(){
        prop = new Properties();
        try {
            String context = System.getProperty("context");
            FileInputStream ip = new FileInputStream("src/test/resources/context/"+context+"/capabilities.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}
