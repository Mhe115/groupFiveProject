package com.example;

//Importing the nessesary iteams to conver a cvs file to a data base so we can use SQL commands on it
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException; // Import the File class
import java.sql.Statement; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files



public class pciGraph {

    



    public static void main(String[] args) {
        

        
        int functionNumber = 0, busId = 0;
        String PCIvendorID = " ", PCIproductID = " ", vendor = " ", deviceId = " ";
        
        String csvFilePath = "pci_devices.csv";

        String sqlQuery = " ";

        String PCIvendorIDlessHex = " ", deviceIDlessHex = " ", PCIproductIDlessHex = " ";

        try {
          File myObj = new File("pci.txt");
          Scanner myReader = new Scanner(myObj);
          while (myReader.hasNextLine()) {
            // Read the values in groups of 5
            if (myReader.hasNextLine()) busId = Integer.parseInt(myReader.nextLine());
            if (myReader.hasNextLine()) deviceId = myReader.nextLine();
            if (myReader.hasNextLine()) functionNumber = Integer.parseInt(myReader.nextLine());
            if (myReader.hasNextLine()) PCIvendorID = myReader.nextLine();
            if (myReader.hasNextLine()) PCIproductID = myReader.nextLine();

            // Printing the values to console
            /*System.out.println("busId: " + busId + ", deviceId: " + deviceId + ", functionNumber: " + functionNumber);
            System.out.println("PCIvendorID: " + PCIvendorID + ", PCIproductID: " + PCIproductID);
            System.out.println("Vendor:" + vendor);
            System.out.println("--------------");*/


            PCIvendorIDlessHex = PCIvendorID.substring(2);
            if (PCIproductID.startsWith("0x")) {
                PCIproductIDlessHex = PCIproductID.substring(2);
            } else {
                PCIproductIDlessHex = PCIproductID;
            }

            
            // SQL query to read all rows from the CSV
            sqlQuery = "SELECT VendorName, DeviceName FROM CSVREAD('" + csvFilePath + "') WHERE LOWER(VENDORID) LIKE LOWER('" + PCIvendorIDlessHex + "') AND LOWER(DeviceID) LIKE LOWER('" + PCIproductIDlessHex + "')";
            //System.out.println(sqlQuery);
            //System.out.println();
            try {
                // Connect to the H2 in-memory database with a specific schema
                Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb");

                // Create a statement object and execute the SQL query
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery);

                // Retrieve metadata to get column names and count
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                /*// Print column names
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metaData.getColumnName(i) + "\t");
                }
                System.out.println();*/

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
