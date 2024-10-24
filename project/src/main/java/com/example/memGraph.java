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

public class memGraph extends JFrame{
  public static void main(String[] args) {
    int l1dCacheSize = 0, l1iCacheSize = 0, l2CacheSize = 0, l3CacheSize = 0;
    String model = " ", socketCount = " ", coresPerSocket = " ";

    try {
      File myObj = new File("CPU.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        // Read the values in groups of 5
        if (myReader.hasNextLine()) model = myReader.nextLine();
        if (myReader.hasNextLine()) socketCount = myReader.nextLine();
        if (myReader.hasNextLine()) coresPerSocket = myReader.nextLine();
        if (myReader.hasNextLine()) l1dCacheSize = Integer.parseInt(myReader.nextLine());
        if (myReader.hasNextLine()) l2CacheSize= Integer.parseInt(myReader.nextLine());
        if (myReader.hasNextLine()) l3CacheSize= Integer.parseInt(myReader.nextLine());

        // Printing the values to console
        //System.out.println("i: " + i + ", j: " + j + ", k: " + k);
        //System.out.println("PCIvendorID: " + PCIvendorID + ", PCIproductID: " + PCIproductID);
        //System.out.println("--------------");

        System.out.println(model + socketCount + coresPerSocket + l1dCacheSize + l1iCacheSize + l2CacheSize + l3CacheSize);
      
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    SwingUtilities.invokeLater(() -> {
    memGraph example = new memGraph("Pie Chart Example");
    example.setSize(800, 600);
    example.setLocationRelativeTo(null);
    example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    example.setVisible(true);
    });





  }

      public memGraph(String title) {
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
