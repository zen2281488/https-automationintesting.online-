package utils.properties;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfProperties {
    private static final Properties commonProperties;
    private static final Properties privateProperties;


    static {
        commonProperties = loadProperties("src/test/resources/conf.properties");
        privateProperties = loadProperties("src/test/resources/confPrivate.properties");
    }

    public static String getCommonProperty(String key) {
        return commonProperties.getProperty(key);
    }

    public static String getPrivateProperty(String key) {
        return privateProperties.getProperty(key);
    }

    public static boolean getCommonBoolProperty(String key) {
        return Boolean.parseBoolean(commonProperties.getProperty(key));
    }

    public static boolean getPrivateBoolProperty(String key) {
        return Boolean.parseBoolean(privateProperties.getProperty(key));
    }

    private static Properties loadProperties(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties from file: " + filePath, e);
        }
    }
}