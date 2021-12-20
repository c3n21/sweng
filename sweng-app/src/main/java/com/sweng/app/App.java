package com.sweng.app;

import java.io.FileNotFoundException;

import org.apache.commons.configuration2.ex.ConfigurationException;

import utils.ConfigurationManager;

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
