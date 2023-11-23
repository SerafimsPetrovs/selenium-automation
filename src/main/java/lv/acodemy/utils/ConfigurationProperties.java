package lv.acodemy.utils;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.util.Properties;

public class ConfigurationProperties {
    public static PropertiesConfiguration getConfiguration(){
        try {
            return new Configurations().properties("configuration.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }


    //try { catch
}
