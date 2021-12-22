package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(8199, configurationManager.getDatabasePort());
    }

    /**
     * testing Database `timeout` in `test.properties`
     * @throws FileNotFoundException
     */
    @Test
    public void testGetDatabaseTimeout() throws FileNotFoundException
    {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance("test.properties");
        assertEquals(60000, configurationManager.getDatabaseTimeout());
    }
    
    /**
     * testing Database `host` in `test.properties`
     * @throws FileNotFoundException
     */
    @Test
    public void testGetDatabaseHost() throws FileNotFoundException
    {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance("test.properties");
        assertEquals("jdbc:mariadb://localhost:3306/utenti",configurationManager.getDatabaseHost());
    }

    /**
     * testing Database `user` in `test.properties`
     * @throws FileNotFoundException
     */
    @Test
    public void testGetDatabaseUser() throws FileNotFoundException
    {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance("test.properties");
        assertEquals("sweng",configurationManager.getDatabaseUser());
    }

    /**
     * testing Database `password` in `test.properties`
     * @throws FileNotFoundException
     */
    @Test
    public void testGetDatabasePassword() throws FileNotFoundException
    {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance("test.properties");
        assertEquals("sweng",configurationManager.getDatabasePassword());
    }
}
