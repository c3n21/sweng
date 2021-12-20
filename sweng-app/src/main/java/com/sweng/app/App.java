package com.sweng.app;

import java.io.FileNotFoundException;

import utils.ConfigurationManager;

import java.sql.*;

public class App 
{
    public static void main( String[] args )
    {
        String filename        = "config.properties";

        ConfigurationManager configurationManager = null;

        try {
            configurationManager = ConfigurationManager.getInstance(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        try {
            Connection connection = DriverManager.getConnection(configurationManager.getDatabaseHost(),
                    configurationManager.getDatabaseUser(),
                    configurationManager.getDatabasePassword()
                );
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Non succede niente?");
    }

}
