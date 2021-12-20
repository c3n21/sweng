/**
* Questa classe si occupa di gestire il file di configurazione `config.properties`
* che contiene i parametri di configurazione di base per il corretto funzionamento
* dell'appliccativo
*/
package utils;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class ConfigurationManager {

    private Configuration config = null;
    private static ConfigurationManager instance = null;

    private ConfigurationManager(String filename) throws FileNotFoundException {
        Configurations configs = new Configurations();
        try {
            config = configs.properties(new File(filename));
        } catch (ConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new FileNotFoundException();
        }
    }

    public static ConfigurationManager getInstance() throws FileNotFoundException {
        return ConfigurationManager.getInstance("config.properties");
    }

    public static ConfigurationManager getInstance(String filename) throws FileNotFoundException {
        if (instance == null) {
            instance = new ConfigurationManager(filename);
        }

        return instance;
    }

    public String getDatabaseHost() {
        return config.getString("database.host");
    }

    public int getDatabaseTimeout() {
        return config.getInt("database.timeout");
    }

    public int getDatabasePort() {
        return config.getInt("database.port");
    }

    public String getDatabaseUser() {
        return config.getString("database.user");
    }

    public String getDatabasePassword() {
        return config.getString("database.password");
    }
}
