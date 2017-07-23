/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linearregressionanalysis;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 *
 * @author Jeremi
 */
public class Data {

    static int dataIndex;
    static double userData;
    static double mean;

    public static double[] dataX = new double[28];
    public static double[] dataY = new double[28];
    DecimalFormat newFormat = new DecimalFormat("#.##");

    Data(int d, double u) {
        dataIndex = d;
        userData = u;
    }

    public static double[][] mainData = {
        //  Y     X1    X2      X3  X4   X5    X6   X7
        {4.9176, 1.0, 3.472, 0.998, 1.0, 7.0, 4.0, 42.0},
        {5.0208, 1.0, 3.531, 1.5, 2.0, 7.0, 4.0, 62.0},
        {4.5429, 1.0, 2.275, 1.175, 1.0, 6.0, 3.0, 40.0},
        {4.5573, 1.0, 4.050, 1.232, 1.0, 6.0, 3.0, 54.0},
        {5.0597, 1.0, 4.455, 1.121, 1.0, 6.0, 3.0, 42.0},
        {3.8910, 1.0, 4.455, 0.988, 1.0, 7.0, 3.0, 56.0},
        {5.8980, 1.0, 5.850, 1.240, 1.0, 7.0, 3.0, 51.0},
        {5.6039, 1.0, 9.520, 1.501, 0.0, 6.0, 3.0, 32.0},
        {16.4202, 2.5, 9.800, 3.420, 2.0, 10.0, 5.0, 42.0},
        {14.4598, 2.5, 12.80, 3.000, 2.0, 9.0, 5.0, 14.0},
        {5.8282, 1.0, 6.435, 1.225, 2.0, 6.0, 3.0, 32.0},
        {5.3003, 1.0, 4.988, 1.552, 1.0, 6.0, 3.0, 30.0},
        {6.2712, 1.0, 5.520, 0.975, 1.0, 5.0, 2.0, 30.0},
        {5.9592, 1.0, 6.666, 1.121, 2.0, 6.0, 3.0, 32.0},
        {5.0500, 1.0, 5.000, 1.020, 0.0, 5.0, 2.0, 46.0},
        {5.6039, 1.0, 9.520, 1.501, 0.0, 6.0, 3.0, 32.0},
        {8.2464, 1.5, 5.150, 1.664, 2.0, 8.0, 4.0, 50.0},
        {6.6969, 1.5, 6.902, 1.488, 1.5, 7.0, 3.0, 22.0},
        {7.7841, 1.5, 7.102, 1.376, 1.0, 6.0, 3.0, 17.0},
        {9.0384, 1.0, 7.800, 1.500, 1.5, 7.0, 3.0, 23.0},
        {5.9894, 1.0, 5.520, 1.256, 2.0, 6.0, 3.0, 40.0},
        {7.5422, 1.5, 4.000, 1.690, 1.0, 6.0, 3.0, 22.0},
        {8.7951, 1.5, 9.890, 1.820, 2.0, 8.0, 4.0, 50.0},
        {6.0931, 1.5, 6.726, 1.652, 1.0, 6.0, 3.0, 44.0},
        {8.3607, 1.5, 9.150, 1.777, 2.0, 8.0, 4.0, 48.0},
        {8.1400, 3.0, 8.000, 1.504, 2.0, 7.0, 3.0, 3.0},
        {9.1416, 1.5, 7.326, 1.831, 1.5, 8.0, 4.0, 31.0},
        {12.0000, 1.5, 5.000, 1.200, 2.0, 6.0, 3.0, 30.0}

    };

    //GET METHODS 
    int getDataIndex() {
        return dataIndex;
    }

    double getUserData() {
        return userData;
    }
//move data from matrix array to normal array 

    double[] getDataX() {

        for (int i = 0; i < 28; i++) {
            dataX[i] = mainData[i][dataIndex];
        }
        return dataX;
    }
//move data from matrix array to normal array 

    double[] getDataY() {

        for (int i = 0; i < 28; i++) {
            dataY[i] = mainData[i][0];
        }
        return dataY;
    }
//calculate the mean of X, first need to use getDataX() method 

    double getMeanOfX() {
        double addUpData = 0.0;
        for (int i = 0; i < 28; i++) {
            addUpData = addUpData + dataX[i];
        }

        return addUpData / 28;
    }
//calculae the mean of Y, first need to use getDataY() method 

    double getMeanOfY() {
        double addUpData = 0.0;
        for (int i = 0; i < 28; i++) {
            addUpData = addUpData + dataY[i];
        }

        return addUpData / 28;
    }

    double summUp(double[] a) {

        double sum = 0;

        for (int i = 0; i < 28; i++) {
            sum = sum + a[i];
        }
        String sumStr = newFormat.format(sum);
        double sumDbl = Double.valueOf(sumStr);

        return sumDbl;
    }
//square array of numbers 

    double[] squareData(double[] x) {

        double[] squared = new double[28];
        for (int i = 0; i < 28; i++) {
            squared[i] = x[i] * x[i];
        }

        return squared;
    }
//multiply two arrays. must have the same length
    double[] multiplyArrays(double[] x, double[] y) {
        double[] result = new double[28];

        for (int i = 0; i < 28; i++) {
            result[i] = x[i] * y[i];
        }

        return result;

    }

    //calculates the slope 
    double b1() {
        getDataX();
        getDataY();
        double meanX = getMeanOfX();
        double meanY = getMeanOfY();
        double sum = 0;

        double[] sX = new double[28];
        double[] sY = new double[28];
        double[] result = new double[28];

        //(x-X^)
        for (int i = 0; i < 28; i++) {
            sX[i] = dataX[i] - meanX;

        }

        //(y-y^)
        for (int i = 0; i < 28; i++) {
            sY[i] = dataY[i] - meanY;
        }

        //SUM (x-x^)
        for (int i = 0; i < 28; i++) {
            sum = sum + sX[i];
        }

        //(x-x^)^2
        squareData(sX);

        double[] sXY = multiplyArrays(sX, sY);

        double denominator = summUp(squareData(sX)); // bottom 
        double numerator = summUp(sXY); //sum (x-x^)(y-y^)

        double slope = numerator / denominator;

        double slopeTwoDecimal = Double.valueOf(newFormat.format(slope));

        return slopeTwoDecimal;
    }

    double b0() {
        double meanY = getMeanOfY();
        double meanX = getMeanOfX();
        double b1 = b1();
        double yIntercept = meanY - b1 * meanX;
        double yInterceptTwoDecimal = Double.valueOf(newFormat.format(yIntercept));

        return yInterceptTwoDecimal;
    }

    double regression(double b1, double b0, double x) {

        double regression = b0 + b1 * x;
        double regressionTwoDecimal = Double.valueOf(newFormat.format(regression));
        return regressionTwoDecimal;
    }

    //RESIDUAL IS NOT FINIHED 

    double resudual(double[] y) {
        double[] yEstimate = new double[28];
        for (int i = 0; i < 28; i++) {
            yEstimate[i] = regression(b1(), b0(), userData);

        }

        for (int i = 0; i < 28; i++) {
            yEstimate[i] = yEstimate[i] - getMeanOfY();
        }

        double residual = summUp(yEstimate);

        return residual;
    }

    double varianceX() {

        double[] rx = dataX;
        double meanOfX = getMeanOfX();

        for (int i = 0; i < 28; i++) {
            rx[i] = rx[i] - meanOfX;
            rx[i] = rx[i] * rx[i];
        }

        //index 0 = X, index 1 = Y
        double result = summUp(rx);

        result = result / 28;

        return Double.valueOf(newFormat.format(result));
    }

    double varianceY() {

        double[] ry = dataY;
        double meanOfY = getMeanOfY();

        for (int i = 0; i < 28; i++) {
            ry[i] = ry[i] - meanOfY;
            ry[i] = ry[i] * ry[i];
        }

        //index 0 = X, index 1 = Y
        double result = summUp(ry);
        result = result / 28;

        return Double.valueOf(newFormat.format(result));
    }
    
    double[] finalSetOfDataX(){
        double[] finalSet = new double[29];
        for (int i = 0; i < 28; i++) {
            finalSet[i] = dataX[i];
        }
        
        finalSet[28] = userData; 
        
        return finalSet; 
    }
    
    double[] finalSetOfDataY(){
        double[] finalSet = new double[29];
        for (int i = 0; i < 28; i++) {
            finalSet[i] = dataY[i];
        }
        
        finalSet[28] = regression(b1(),b0(),userData); 
        
        return finalSet; 
    }
    
    
    
    
    double[] allForecast(){
        double[] forecast = new double[29];
        for (int i = 0; i < 28; i++) {
            forecast[i] = dataY[i];
        }
        
        forecast[28] = regression(b1(),b0(),userData); 
        
        return forecast; 
    }
    
    

    //SET METHODS 
    void setDataIndex(int i) {
        dataIndex = i;
    }

    void setUserData(double u) {
        userData = u;
    }

//    void tabledata() {
//
//        //data summary 2 
//        AnalysisGUI.dataSummary2Data[0][1] = String.valueOf((double) Math.round(summUp(getDataX()) * 10) / 10);
//
//    }
    //ACTION METHODS 
    //sums all values within arrya (28), accepts 28 long array as a parameter 
}//end of class 
