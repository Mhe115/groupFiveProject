package com.example;

//Importing the nessesary iteams to conver a cvs file to a data base so we can use SQL commands on it
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;



public class pciGraph {

    public static void main(String[] args) {
        

        
        int functionNumber = 0, deviceId = 0, busId = 0;
        String PCIvendorID = " ", PCIproductID = " ", vendor = " ";
        
        String csvFilePath = "pci_devices.csv";

        String sqlQuery = " ";

        try {
          File myObj = new File("pci.txt");
          Scanner myReader = new Scanner(myObj);
          while (myReader.hasNextLine()) {
            // Read the values in groups of 5
            if (myReader.hasNextLine()) busId = Integer.parseInt(myReader.nextLine());
            if (myReader.hasNextLine()) deviceId = Integer.parseInt(myReader.nextLine());
            if (myReader.hasNextLine()) functionNumber = Integer.parseInt(myReader.nextLine());
            if (myReader.hasNextLine()) PCIvendorID = myReader.nextLine();
            if (myReader.hasNextLine()) PCIproductID = myReader.nextLine();

            // Printing the values to console
            System.out.println("busId: " + busId + ", deviceId: " + deviceId + ", functionNumber: " + functionNumber);
            System.out.println("PCIvendorID: " + PCIvendorID + ", PCIproductID: " + PCIproductID);
            System.out.println("Vendor:" + vendor);
            System.out.println("--------------");

        // SQL query to read all rows from the CSV
        sqlQuery = "SELECT * FROM CSVREAD('" + csvFilePath + "')WHERE VENDORID LIKE " + PCIvendorID;

        try {
            // Connect to the H2 in-memory database with a specific schema
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb");

            // Create a statement object and execute the SQL query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            // Retrieve metadata to get column names and count
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Print column names
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();

            // Print rows dynamically
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }

            // Close the resources
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }

          }
          myReader.close();
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
    
        
        


    }
}
