package com.example;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class App {
  public static void main(String[] args) {
    int i = 0, j = 0, k = 0;
    String PCIvendorID = " ", PCIproductID = " ";

    try {
      File myObj = new File("pci.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        // Read the values in groups of 5
        if (myReader.hasNextLine()) i = Integer.parseInt(myReader.nextLine());
        if (myReader.hasNextLine()) j = Integer.parseInt(myReader.nextLine());
        if (myReader.hasNextLine()) k = Integer.parseInt(myReader.nextLine());
        if (myReader.hasNextLine()) PCIvendorID = myReader.nextLine();
        if (myReader.hasNextLine()) PCIproductID = myReader.nextLine();

        // Printing the values to console
        System.out.println("i: " + i + ", j: " + j + ", k: " + k);
        System.out.println("PCIvendorID: " + PCIvendorID + ", PCIproductID: " + PCIproductID);
        System.out.println("--------------");
      
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    




  }
}
