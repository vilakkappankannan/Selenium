package configurations;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class PropertiesConfig {
    public Properties readProperties(){
        try (InputStream input = PropertiesConfig.class.getClassLoader().getResourceAsStream("environment.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                log.error("Sorry, unable to find data in environment.properties");
                return prop;
            }
            //load a properties file from class path, inside static method
            prop.load(input);
            return prop;
        } catch (IOException ex) {
            throw new RuntimeException("environment.properties not exit !!!");
        }

    }

}
