package linearregressionanalysis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LinearRegressionAnalysis extends JFrame implements ActionListener {

    static double data = 0.0;
    static int dataIndex = 0; 

    //ImageIcon logoIcon = (new javax.swing.ImageIcon(getClass().getResource("/linearregressionanalysis/images/LRA_Logo.png")));
    JLabel logoLabel = new JLabel("");

    JLabel x1 = new JLabel("Number of bathrooms");
    static JTextField x1tf = new JTextField(10);
    JButton x1btn = new JButton("Analyse");

    JLabel x2 = new JLabel("Area of the site (1000's square feet)");
    JTextField x2tf = new JTextField(10);
    JButton x2btn = new JButton("Analyse");

    JLabel x3 = new JLabel(" Size of living space(1000's square feet)");
    JTextField x3tf = new JTextField(10);
    JButton x3btn = new JButton("Analyse");

    JLabel x4 = new JLabel("Number of garages");
    JTextField x4tf = new JTextField(10);
    JButton x4btn = new JButton("Analyse");

    JLabel x5 = new JLabel("Number of rooms");
    JTextField x5tf = new JTextField(10);
    JButton x5btn = new JButton("Analyse");

    JLabel x6 = new JLabel("Number of bedrooms");
    JTextField x6tf = new JTextField(10);
    JButton x6btn = new JButton("Analyse");

    JLabel x7 = new JLabel("Age (years");
    JTextField x7tf = new JTextField(10);
    JButton x7btn = new JButton("Analyse");

    

    public static void main(String[] args) {

        new LinearRegressionAnalysis();

    }//end of main method 

    LinearRegressionAnalysis() {

        this.setTitle("Linear Regression Analysis - Create New Analysis");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        //TOP Panel setup 
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(78, 149, 255));
        topPanel.setPreferredSize(new Dimension(0, 90));

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/linearregressionanalysis/images/LRA_Logo.png")));
        topPanel.add(logoLabel);

        //CENTER panel setup 
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.white);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        //centerPanel.setPreferredSize(new Dimension(350, 100));

        x1btn.addActionListener(this);
        x2btn.addActionListener(this);
        x3btn.addActionListener(this);
        x4btn.addActionListener(this);
        x5btn.addActionListener(this);
        x6btn.addActionListener(this);
        x7btn.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(x1, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        centerPanel.add(x1tf, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        centerPanel.add(x1btn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(x2, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        centerPanel.add(x2tf, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        centerPanel.add(x2btn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(x3, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        centerPanel.add(x3tf, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        centerPanel.add(x3btn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        centerPanel.add(x4, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        centerPanel.add(x4tf, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        centerPanel.add(x4btn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        centerPanel.add(x5, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        centerPanel.add(x5tf, gbc);
        gbc.gridx = 2;
        gbc.gridy = 4;
        centerPanel.add(x5btn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        centerPanel.add(x6, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        centerPanel.add(x6tf, gbc);
        gbc.gridx = 2;
        gbc.gridy = 5;
        centerPanel.add(x6btn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        centerPanel.add(x7, gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        centerPanel.add(x7tf, gbc);
        gbc.gridx = 2;
        gbc.gridy = 6;
        
        centerPanel.add(x7btn, gbc);

        //Adding to main manels 
        this.add(topPanel, "North");
        this.add(centerPanel, "Center");

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        
        
        if (e.getSource() == x1btn) {
            data = Double.parseDouble(x1tf.getText());
            dataIndex = 1; 
            new AnalysisGUI();
        } else if (e.getSource() == x2btn) {
            data = Double.parseDouble(x2tf.getText());
            dataIndex =2; 
            new AnalysisGUI();

        } else if (e.getSource() == x3btn) {
            data = Double.parseDouble(x3tf.getText());
            dataIndex = 3; 
            new AnalysisGUI();

        } else if (e.getSource() == x4btn) {
            data = Double.parseDouble(x4tf.getText());
            dataIndex = 4; 
            new AnalysisGUI();

        } else if (e.getSource() == x5btn) {
            data = Double.parseDouble(x5tf.getText());
            dataIndex = 5; 
            new AnalysisGUI();

        } else if (e.getSource() == x6btn) {
            data = Double.parseDouble(x6tf.getText());
            dataIndex = 6;
            new AnalysisGUI();

        } else if (e.getSource() == x7btn) {
            data = Double.parseDouble(x7tf.getText());
            dataIndex = 7; 
            new AnalysisGUI();

        }
        
        dispose();
        
        
       
        

    }// end of action listener 

    void setData(double a) {
        a = data;
    }

    static double getData() {

        return data;                          
    }

}
