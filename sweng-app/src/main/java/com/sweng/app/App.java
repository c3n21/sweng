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
        

        System.out.println("Non succede niente?");
    }

}
