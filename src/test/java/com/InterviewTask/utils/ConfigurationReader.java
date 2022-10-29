package com.InterviewTask.utils;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * This util class is used to read values from configuratuon.properties file
 */

public class ConfigurationReader {

    private static Properties properties = new Properties();


    static {
        try {
            //open the file input stream
            FileInputStream inputStream = new FileInputStream("configuration.properties");

            //load to properties object
            properties.load(inputStream);

            //close the file after loading.Free up memory
            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while reading configuration file");
        }
    }


        public static String getProperty(String key){
            return properties.getProperty(key);
        }




}
