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
        // Absolute path to your CSV file
        String csvFilePath = "pci_devices.csv";

        // SQL query to read all rows from the CSV
        String sqlQuery = "SELECT * FROM CSVREAD('" + csvFilePath + "')";

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
}
