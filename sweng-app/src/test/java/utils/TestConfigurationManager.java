package utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

/**
 * Unit test for {@link ConfigurationManager}
 */
public class TestConfigurationManager {


    /**
     * testing Database `port` in `test.properties`
     * @throws FileNotFoundException
     */
    @Test
    public void testGetDatabasePort() throws FileNotFoundException
    {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance("test.properties");
        assertTrue(configurationManager.getDatabasePort() == 12345);
    }

    /**
     * testing Database `timeout` in `test.properties`
     * @throws FileNotFoundException
     */
    @Test
    public void testGetDatabaseTimeout() throws FileNotFoundException
    {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance("test.properties");
        assertTrue(configurationManager.getDatabaseTimeout() == 60000);
    }
    
    /**
     * testing Database `host` in `test.properties`
     * @throws FileNotFoundException
     */
    @Test
    public void testGetDatabaseHost() throws FileNotFoundException
    {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance("test.properties");
        assertTrue(configurationManager.getDatabaseHost().equals("test.host"));
    }

    /**
     * testing Database `user` in `test.properties`
     * @throws FileNotFoundException
     */
    @Test
    public void testGetDatabaseUser() throws FileNotFoundException
    {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance("test.properties");
        assertTrue(configurationManager.getDatabaseUser().equals("test-admin"));
    }

    /**
     * testing Database `password` in `test.properties`
     * @throws FileNotFoundException
     */
    @Test
    public void testGetDatabasePassword() throws FileNotFoundException
    {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance("test.properties");
        assertTrue(configurationManager.getDatabasePassword().equals("test-password"));
    }
}
