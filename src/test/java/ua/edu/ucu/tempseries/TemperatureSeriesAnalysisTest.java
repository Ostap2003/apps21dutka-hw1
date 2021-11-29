package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {0.0, 1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.5;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.deviation();
    }

    @Test
    public void testDeviationWithOneElementArray() {
        double[] temperatureSeries = {1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double expResult = 0.0;
        double actualResult = seriesAnalysis.deviation();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {2.2, 2.1, 2.01, 1.98};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double expResult = 1.98;
        double actualResult = seriesAnalysis.min();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {2.2, 2.1, 2.01, 1.98};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double expResult = 2.2;
        double actualResult = seriesAnalysis.max();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testWithMaxPossibleDouble() {
        double[] temperatureSeries = {Double.MAX_VALUE, Double.MAX_VALUE - 1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double expResult = Double.MAX_VALUE;
        double actualResult = seriesAnalysis.max();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testWithMinPossibleDouble() {
        double[] temperatureSeries = {-273.0, -272.99};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double expResult = -273.0;
        double actualResult = seriesAnalysis.min();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArr() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.min();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithEmptyArr() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.max();
    }

    @Test
    public void testFindClosestToZero() {
        double[] tempSeries = {-0.1, 0.2};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(tempSeries);

        double expRes = -0.1;
        double actRes = seriesAnalysis.findTempClosestToZero();
        assertEquals(expRes, actRes, 0.00001);
    }

    @Test
    public void testFindClosestToZeroSameDist() {
        double[] tempSeries = {-0.1, 0.1};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(tempSeries);
        double expRes = 0.1;
        double actRes = seriesAnalysis.findTempClosestToZero();
        assertEquals(expRes, actRes, 0.00001);
    }

    @Test
    public void testFindClosestToValue() {
        double[] tempSeries = {0.5, 0,97, 0.99, 1.02};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(tempSeries);
        double expRes = 0.99;
        double actRes = seriesAnalysis.findTempClosestToValue(1.0);
        assertEquals(expRes, actRes, 0.00001);

        double[] tempSeriesPart2 = {6.0, -10, -5, 5.5};
        TemperatureSeriesAnalysis seriesAnalysisPart2 = new TemperatureSeriesAnalysis(tempSeriesPart2);
        double expResP2 = -5;
        double actResP2 = seriesAnalysisPart2.findTempClosestToValue(0.1);
        assertEquals(expResP2, actResP2, 0.00001);

    }

    @Test
    public void testFindClosestToValueSameDist() {
        double[] tempSeries = {-2.2, -2.1};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(tempSeries);
        double expRes = -2.1;
        double actRes = seriesAnalysis.findTempClosestToValue(-2.15);
        assertEquals(expRes, actRes, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindClosestToValueEmptyArr() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.findTempClosestToValue(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindClosestToZeroEmptyArr() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.findTempClosestToZero();
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] tempSeries = {-1.5, 2.4, -5.5, 3.1};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(tempSeries);
        double[] expRes = {2.4, 3.1};
        double[] actRes = seriesAnalysis.findTempsGreaterThen(2.4);
        assertArrayEquals(expRes, actRes, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThenEmptyResult() {
        double[] tempSeries = {1, 2, 3};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(tempSeries);
        double[] expRes = new double[0];
        double[] actRes = seriesAnalysis.findTempsGreaterThen(4);
        assertArrayEquals(expRes, actRes, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThenEmptyArr() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();

        double[] expRes = new double[0];
        double[] actRes = seriesAnalysis.findTempsGreaterThen(0);
        assertArrayEquals(expRes, actRes, 0.00001);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] tempSeries = {-1.5, 2.4, -5.5, 3.1};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(tempSeries);
        double[] expRes = {-1.5, -5.5};
        double[] actRes = seriesAnalysis.findTempsLessThen(2.4);
        assertArrayEquals(expRes, actRes, 0.00001);
    }

    @Test
    public void testFindTempsLessThenEmptyResult() {
        double[] tempSeries = {1, 2, 3};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(tempSeries);
        double[] expRes = new double[0];
        double[] actRes = seriesAnalysis.findTempsLessThen(1);
        assertArrayEquals(expRes, actRes, 0.00001);
    }

    @Test
    public void testFindTempsLessThenEmptyArr() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        double[] expRes = new double[0];
        double[] actRes = seriesAnalysis.findTempsLessThen(0);
        assertArrayEquals(expRes, actRes, 0.00001);
    }

    @Test
    public void testSummaryStatistics() {
        double[] tempSeries = {0.0, 1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(tempSeries);
        TempSummaryStatistics summaryStatistics = seriesAnalysis.summaryStatistics();
        assertSame(summaryStatistics.getClass(), TempSummaryStatistics.class);

        String actRes = summaryStatistics.toString();
        String expRes = "TempSummaryStatistics{" +
                "avgTemp=0.5" +
                ", devTemp=0.5" +
                ", minTemp=0.0" +
                ", maxTemp=1.0" +
                '}';
        assertEquals(actRes, expRes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSummaryStatisticsEmptyArr() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.summaryStatistics();
    }

    @Test
    public void testAddTemps() {
        double[] tempSeries = {1, 2, 3};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(tempSeries);

        int expRes = 4;
        int actRes = seriesAnalysis.addTemps(5);
        assertEquals(expRes, actRes);
    }

    @Test
    public void testAddTempsAddEmptyArr() {
        double[] tempSeries = {1, 2, 3};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(tempSeries);
        int expRes = 3;
        int actRes = seriesAnalysis.addTemps();
        assertEquals(expRes, actRes);
    }

    @Test
    public void testAddTempsMultipleArgs() {
        double[] tempSeries = {1, 2, 3};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(tempSeries);
        int expRes = 6;
        int actRes = seriesAnalysis.addTemps(1, 2, 3);
        assertEquals(expRes, actRes);
    }

    @Test
    public void testAddTempsToEmptyArr() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        int expRes = 3;
        int actRes = seriesAnalysis.addTemps(1, 2, 3);
        assertEquals(expRes, actRes);
    }

    @Test
    public void testFunctionalityAfterTempsAddition() {
        double [] tempSeries = {0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(tempSeries);
        seriesAnalysis.addTemps(1);

        assertEquals(0.5, seriesAnalysis.average(), 0.00001);
        assertEquals(0.5, seriesAnalysis.deviation(), 0.00001);
        assertEquals(0, seriesAnalysis.min(), 0.00001);
        assertEquals(1, seriesAnalysis.max(), 0.00001);
    }

    @Test
    public void testToString() {
        double[] tempSeries = {1, 2, 3};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(tempSeries);
        String expRes = "TemperatureSeriesAnalysis[1.0, 2.0, 3.0]";
        String actRes = seriesAnalysis.toString();
        assertEquals(expRes, actRes);
    }
}
