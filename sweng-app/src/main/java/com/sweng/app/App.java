package com.sweng.app;

import java.io.FileNotFoundException;

import gui.Gui;
import utils.ConfigurationManager;

public class App {
    public static void main( String[] args )
    {
        String filename = "config.properties";

        try {
            ConfigurationManager.getInstance(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Gui.main(args);
    }

}
