package util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertyReader {

    private static final String DEFAULT_PROP_FILE = Constants.TEST_CONFIG_FILE;
    private static final Properties properties = new Properties();
    static {
        ClassLoader classLoader = PropertyReader.class.getClassLoader();
        try{
            System.out.println("user.dir"+ System.getProperty("user.dir"));
            String propertyFile = classLoader.getResource(DEFAULT_PROP_FILE).getFile();
            loadPropertiesFrom(propertyFile);
        }catch (Exception e){
            throw new RuntimeException("Unable to load property file " + DEFAULT_PROP_FILE + ". Reason", e);
        }
    }

    public static void clearProperties(){
        properties.clear();
    }

    public static void loadPropertiesFrom(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        properties.load(fileReader);
    }

    public static String getProperty(String keyName){
        return properties.getProperty(keyName);
    }

}
