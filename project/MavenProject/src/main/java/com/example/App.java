package com.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    public App(String title) {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App example = new App("Pie Chart Example");
            example.setSize(800, 600);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}