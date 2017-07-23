package linearregressionanalysis;

import com.xeiam.xchart.Chart;
import com.xeiam.xchart.Series;
import com.xeiam.xchart.SeriesLineStyle;
import com.xeiam.xchart.SeriesMarker;
import com.xeiam.xchart.StyleManager;
import com.xeiam.xchart.StyleManager.LegendPosition;
import com.xeiam.xchart.XChartPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.*;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class AnalysisGUI extends JFrame implements ActionListener {

    //static double data2 = LinearRegressionAnalysis.getData(); 
    Data data = new Data(LinearRegressionAnalysis.dataIndex, LinearRegressionAnalysis.data);
    DecimalFormat newFormat = new DecimalFormat("#.##");

    JButton button1 = new JButton("Some button");
    JButton newAnalysis = new JButton("New Analysis");
    JLabel logoLabel = new JLabel("");

    public static void main(String[] args) {
        new AnalysisGUI();
    }

    AnalysisGUI() {

        System.out.println("User Data: " + data.getUserData() + " Data Index: " + data.getDataIndex());
        data.getDataX();
        data.getDataY();
        System.out.println("Data X: " + Arrays.toString(Data.dataX));
        System.out.println("Data Y: " + Arrays.toString(Data.dataY));

        System.out.println("Slope: " + data.b1());
        System.out.println("Intercept: " + data.b0());
        System.out.println("Regression " + data.regression(data.b1(), data.b0(), data.getUserData()));
        System.out.println("Mean X: " + data.getMeanOfX() + " Mean Y: " + data.getMeanOfY());

        this.setTitle("Linear Regression Analysis - Create New Analysis");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        //this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(78, 149, 255));
        topPanel.setPreferredSize(new Dimension(0, 90));

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/linearregressionanalysis/images/LRA_Logo.png")));
        topPanel.add(logoLabel);

        JPanel topPanelLeft = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanelLeft.setPreferredSize(new Dimension(840, 30));
        topPanelLeft.setBackground(new Color(78, 149, 255));
        topPanel.add(topPanelLeft);
        topPanelLeft.add(newAnalysis);

        //Setting up JTabbed Panel 
        JTabbedPane tabPanel = new JTabbedPane();
        JComponent tab1 = makeCharts();
        tabPanel.addTab("Linear Regression Analysis Results - Graphs", tab1);

        JComponent tab2 = mainData("Panel #2");
        tabPanel.addTab("Data and Calculations", tab2);

        tabPanel.setPreferredSize(new Dimension(1255, 700));

        this.add(topPanel, "North");
        this.add(tabPanel, "Center");
        tabPanel.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        this.setVisible(true);
        button1.addActionListener(this);
        newAnalysis.addActionListener(this);

    }

    protected JComponent makeCharts() {
        JPanel panel = new JPanel(false);
        JLabel filter = new JLabel("text");
        filter.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);

//        CreateChart chart = new CreateChart();
        panel.add(createChartNoline("Regression Analysis", 600, 500));
        panel.add(createChart("Best Fit Line", 600, 500));
        panel.validate();

        return panel;

        ///////////
    }

    protected JComponent mainData(String a) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        //---------------------- JTABLES  ---------------------------------------// 
        JLabel inputL = new JLabel("Input Data");
        JLabel dataSummaryL = new JLabel("Data Summary (i)");
        JLabel dataSummary2L = new JLabel("Data Summary (ii)");
        JLabel regressionResultsL = new JLabel("Linear Regression Analysis Results");
        JLabel forecastValL = new JLabel("Forecast Value Y ");

        String[] inputDataColumns = {"Y", "X"};
        Object[][] inputDataData = new Object[28][2];
        //add data to inputDataData 
        for (int i = 0; i < Data.dataY.length; i++) {
            inputDataData[i][0] = Data.dataY[i];
            inputDataData[i][1] = Data.dataX[i];

        }

        JTable inputData = new JTable(inputDataData, inputDataColumns);

        String[] dataSummaryColumns = {" ", "X", "Y"};
        Object[][] dataSummaryData = {
            {"N", Data.dataX.length, Data.dataY.length},
            {"Mean", newFormat.format(data.getMeanOfX()), newFormat.format(data.getMeanOfY())},
            {"Variance", data.varianceX(), data.varianceY()},
            {"Sd. Dev", "/", "/"}
        };
        JTable dataSummary = new JTable(dataSummaryData, dataSummaryColumns);

        String[] dataSummary2Columns = {" ", " ",};
        Object[][] dataSummary2Data = {
            {"\u03A3 X", data.summUp(Data.dataX)},
            {"\u03A3 X \u00b2", data.summUp(data.squareData(Data.dataX))},
            {"\u03A3 Y \u00b2", data.summUp(Data.dataY)},
            {"\u03A3 Y \u00b2", data.summUp(data.squareData(Data.dataY)),},
            {"\u03A3 XY", data.summUp(data.multiplyArrays(Data.dataX, Data.dataY)),}
        };
        JTable dataSummary2 = new JTable(dataSummary2Data, dataSummary2Columns);

        String[] lraResultsColumns = {"R", "R\u00b2", "Slope", "Y Intercept", "Std. Error Estimate "};
        Object[][] lraResultsData = {{"/", "/", data.b1(), data.b0(), "/"}};
        JTable lraResults = new JTable(lraResultsData, lraResultsColumns);

        String[] forecastValueColumns = {"X", "Forecasted Y"};
        Object[][] forecastValueData = {{data.getUserData(), data.regression(data.b1(), data.b0(), Data.userData)}};
        JTable forecastValue = new JTable(forecastValueData, forecastValueColumns);

        //---------------------- SCROLL PANES FOR TABLES AND OTHER SETUP ---------------------------------------// 
        JScrollPane scrollPaneTable1 = new JScrollPane(inputData);

        inputData.setFillsViewportHeight(
                true);
        inputData.setPreferredScrollableViewportSize(
                new Dimension(250, 150));

        JScrollPane scrollPaneTable2 = new JScrollPane(dataSummary);

        dataSummary.setFillsViewportHeight(
                true);
        dataSummary.setPreferredScrollableViewportSize(
                new Dimension(200, 300));

        JScrollPane scrollPaneTable3 = new JScrollPane(dataSummary2);

        dataSummary2.setPreferredScrollableViewportSize(
                new Dimension(200, 300));
        dataSummary2.setFillsViewportHeight(
                true);

        JScrollPane scrollPaneTable4 = new JScrollPane(lraResults);

        lraResults.setPreferredScrollableViewportSize(
                new Dimension(200, 300));
        lraResults.setFillsViewportHeight(
                true);

        JScrollPane scrollPaneTable5 = new JScrollPane(forecastValue);

        forecastValue.setPreferredScrollableViewportSize(
                new Dimension(200, 300));
        forecastValue.setFillsViewportHeight(
                true);

        gbc.weighty = 0;
        gbc.gridx = 1;
        gbc.gridy = 2;

        panel.add(inputL, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weighty = 1;

        panel.add(scrollPaneTable1, gbc);

        gbc.weighty = 0;
        gbc.gridx = 2;
        gbc.gridy = 2;

        panel.add(dataSummaryL, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weighty = 1;

        panel.add(scrollPaneTable2, gbc);

        gbc.weighty = 0;
        gbc.gridx = 3;
        gbc.gridy = 2;

        panel.add(dataSummary2L, gbc);
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.weighty = 1;

        panel.add(scrollPaneTable3, gbc);

        gbc.weighty = 0;
        gbc.gridx = 1;
        gbc.gridy = 4;

        panel.add(regressionResultsL, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weighty = 1;

        panel.add(scrollPaneTable4, gbc);

        gbc.weighty = 0;
        gbc.gridx = 2;
        gbc.gridy = 4;

        panel.add(forecastValL, gbc);
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.weighty = 1;

        panel.add(scrollPaneTable5, gbc);

        return panel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println(LinearRegressionAnalysis.getData());
        //System.out.println(data.getDataindex());

        //   data.regression(data.b1(), data.b0(), data.getUserData());
        //data.resudual(data.getDataY());
        System.out.println("kurwaaaaaa" + data.b1());
        if (e.getSource() == newAnalysis) {
            new LinearRegressionAnalysis();
            dispose();
        }

    }
    
    //create chart with regression line 
    JComponent createChart(String t, int w, int h) {

        int sizeX;

        if (data.getDataIndex() == 7) {
            sizeX = 65;
        } else {
            sizeX = 20;
        }
        String x;
        switch (Data.dataIndex) {
            case 1:
                x = "Number of bathrooms";
                break;
            case 2:
                x = "Area of the site (1000's square feet)";
                break;
            case 3:
                x = "Size of living space (1000's square feet)";
                break;
            case 4:
                x = "Number of garages";
                break;
            case 5:
                x = "number of rooms";
                break;
            case 6:
                x = "Number of edrooms";
                break;
            case 7:
                x = "Age (years)";
                break;
            default:
                x = "X";
                break;
        }
        
        
        //the following code for creating charts is from http://xeiam.com/xchart-example-code/

        com.xeiam.xchart.Chart chart = new com.xeiam.xchart.Chart(w, h);
        chart.setChartTitle(t);
        chart.setXAxisTitle(x);
        chart.setYAxisTitle("Y - House Price");
        chart.getStyleManager().setLegendPosition(StyleManager.LegendPosition.InsideSW);

        double[] xData = data.getDataX();
        double[] yData = data.getDataY();
        double[] yData2 = new double[]{data.b0(),data.regression(data.b1(), data.b0(), data.getUserData())};
        double[] xData2 = new double[]{0,data.getUserData()};

        Series series = chart.addSeries("A", xData, yData);
        series.setLineStyle(SeriesLineStyle.NONE);
        series.setMarker(SeriesMarker.DIAMOND);
        series.setMarkerColor(Color.BLACK);

        Series series2 = chart.addSeries("B", xData2, yData2);
        series2.setMarker(SeriesMarker.NONE);
        series2.setLineStyle(SeriesLineStyle.DASH_DASH);
        series2.setLineColor(Color.RED);

        chart.getStyleManager().setYAxisLogarithmic(false);

        chart.getStyleManager().setYAxisMin(0);
        chart.getStyleManager().setYAxisMax(35);

        chart.getStyleManager().setXAxisMin(0);
        chart.getStyleManager().setXAxisMax(sizeX);

        JPanel chartPanel = new XChartPanel(chart);

        return chartPanel;

    }
    
    //create chart without regression line 
    JComponent createChartNoline(String t, int w, int h) {

        int sizeX;

        if (data.getDataIndex() == 7) {
            sizeX = 65;
        } else {
            sizeX = 20;
        }
        String x;
        switch (Data.dataIndex) {
            case 1:
                x = "Number of bathrooms";
                break;
            case 2:
                x = "Area of the site (1000's square feet)";
                break;
            case 3:
                x = "Size of living space (1000's square feet)";
                break;
            case 4:
                x = "Number of garages";
                break;
            case 5:
                x = "number of rooms";
                break;
            case 6:
                x = "Number of edrooms";
                break;
            case 7:
                x = "Age (years)";
                break;
            default:
                x = "X";
                break;
        }
        
        
        //the following code for creating charts is from http://xeiam.com/xchart-example-code/

        com.xeiam.xchart.Chart chart = new com.xeiam.xchart.Chart(w, h);
        chart.setChartTitle(t);
        chart.setXAxisTitle(x);
        chart.setYAxisTitle("Y - House Price");
        chart.getStyleManager().setLegendPosition(StyleManager.LegendPosition.InsideSW);

        double[] xData = data.getDataX();
        double[] yData = data.getDataY();
       // double[] yData2 = new double[]{data.b0(),data.regression(data.b1(), data.b0(), data.getUserData())};
        //double[] xData2 = new double[]{0,data.getUserData()};

        Series series = chart.addSeries("A", xData, yData);
        series.setLineStyle(SeriesLineStyle.NONE);
        series.setMarker(SeriesMarker.DIAMOND);
        series.setMarkerColor(Color.BLACK);

       

        chart.getStyleManager().setYAxisLogarithmic(false);

        chart.getStyleManager().setYAxisMin(0);
        chart.getStyleManager().setYAxisMax(35);

        chart.getStyleManager().setXAxisMin(0);
        chart.getStyleManager().setXAxisMax(sizeX);

        JPanel chartPanel = new XChartPanel(chart);

        return chartPanel;

    }

}

//my notes 

/*

 String.valueOf((double)Math.round(data.getMeanOfX() * 10) / 10);

 */
