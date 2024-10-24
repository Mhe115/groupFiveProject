package com.example;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class pciGraph extends JFrame{
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

    SwingUtilities.invokeLater(() -> {
    pciGraph example = new pciGraph("Pie Chart Example");
    example.setSize(800, 600);
    example.setLocationRelativeTo(null);
    example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    example.setVisible(true);
    });





  }

      public pciGraph(String title) {
        super(title);

        // Create a dataset
        DefaultPieDataset dataset = createDataset();

        // Create the chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Programming Language Usage",  // Chart title
                dataset,                       // Dataset
                true,                          // Include legend
                true,
                false
        );

        // Customize the chart
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Java", new Color(0, 128, 0));
        plot.setSectionPaint("Python", new Color(0, 0, 255));
        plot.setSectionPaint("C++", new Color(255, 0, 0));

        // Add the chart to a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    private DefaultPieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Java", 40);
        dataset.setValue("Python", 30);
        dataset.setValue("C++", 20);
        dataset.setValue("JavaScript", 10);
        return dataset;
    }
}
