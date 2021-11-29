package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.lang.Math;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] tempertureSeries;
    private int seriesLen;
    private final int minPossibleTemp = -273;

    public TemperatureSeriesAnalysis() {
        this.tempertureSeries = new double[1];
        this.seriesLen = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        checkTemps(temperatureSeries);
        this.tempertureSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
        this.seriesLen = temperatureSeries.length;
    }

    public double average() {
        if (seriesLen == 0){
            throw new IllegalArgumentException();
        }
        double tempSum = 0;
        for (int i = 0; i < seriesLen; i++) {
            tempSum += tempertureSeries[i];
        }
        return tempSum / seriesLen;
    }

    public double deviation() {
        if (seriesLen == 0){
            throw new IllegalArgumentException();
        }
        double tempMean = this.average();
        double tempVar= 0;
        for (int i = 0; i < seriesLen; i++) {
            tempVar += Math.pow(tempertureSeries[i] - tempMean, 2) / seriesLen;
        }
        return Math.pow(tempVar, 0.5);
    }

    public double min() {
        if (seriesLen == 0){
            throw new IllegalArgumentException();
        }
        return this.findTempClosestToValue(minPossibleTemp);
    }

    public double max() {
        if (seriesLen == 0){
            throw new IllegalArgumentException();
        }
        double maxTemp = Double.MIN_VALUE;

        for (int i = 0; i < seriesLen; i++) {
            if (tempertureSeries[i] > maxTemp) {
                maxTemp = tempertureSeries[i];
            }
        }
        return maxTemp;
    }

    public double findTempClosestToZero() {
        if (seriesLen == 0){
            throw new IllegalArgumentException();
        }
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (seriesLen == 0) {
            throw new IllegalArgumentException();
        }

        double closestToVal = 0; // was double.MAX_VALUE
        double smallestDist = Double.MAX_VALUE;

        for (int i = 0; i < seriesLen; i++) {
            double dist = Math.abs(tempValue - tempertureSeries[i]);
            if (dist < smallestDist) {
                smallestDist = dist;
                closestToVal = tempertureSeries[i];
            }
            // if dists are the same, we return positive temp
            if (dist == smallestDist) {
                if (tempertureSeries[i] > closestToVal) {
                    smallestDist = dist;
                    closestToVal = tempertureSeries[i];
                }
            }
        }
        return closestToVal;
    }

    private double[] findTemps(double tempValue, int mode) {
        double[] tempsFound = new double[this.tempertureSeries.length];
        int i = 0;
        int j = 0;
        while (i != seriesLen) {
            // if we're looking for temps less than tempValue
            if (tempertureSeries[i] < tempValue && mode == 1) {
                tempsFound[j] = tempertureSeries[i];
                j++;
            }
            // if we're looking for greater temps than tempValue
            else if (tempertureSeries[i] >= tempValue && mode == 0) {
                tempsFound[j] = tempertureSeries[i];
                j++;
            }
            i++;
        }
        return Arrays.copyOf(tempsFound, j);
    }

    public double[] findTempsLessThen(double tempValue) {
        return this.findTemps(tempValue, 1);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        return this.findTemps(tempValue, 0);
    }

    public TempSummaryStatistics summaryStatistics() {
        if (seriesLen == 0) {
            throw new IllegalArgumentException();
        }
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        for (int i = 0; i < temps.length; i++) {
            // if array is filled, extend it 2x
            if (seriesLen == tempertureSeries.length) {
                this.tempertureSeries = Arrays.copyOf(tempertureSeries, tempertureSeries.length * 2);
            }
            tempertureSeries[seriesLen] = temps[i];
            seriesLen++;
        }
        return seriesLen;
    }

    private void checkTemps(double[] temperatureSeries) {
        for (int i = 0; i < seriesLen; i++) {
            if (tempertureSeries[i] < minPossibleTemp) {
                throw new InputMismatchException();
            }
        }
    }

    public String toString() {
        return "TemperatureSeriesAnalysis" + Arrays.toString(this.tempertureSeries);
    }
}
