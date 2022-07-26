package com.example.myapplication;

import android.graphics.Color;
import android.graphics.Typeface;

import com.example.myapplication.CustomRenderer.CustomPieChartRenderer;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.text.DecimalFormat;
import java.util.List;

public class PieChartManager {

    private final boolean mTemp;
    public PieChart pieChart;

    public PieChartManager(PieChart pieChart, boolean temp) {
        this.pieChart = pieChart;
        this.mTemp = temp;
        initPieChart();
    }


    //initialization
    private void initPieChart() {

        CustomPieChartRenderer customPieChartRenderer = new CustomPieChartRenderer(pieChart, pieChart.getAnimator(), pieChart.getViewPortHandler(), mTemp, 10f);
        pieChart.setRenderer(customPieChartRenderer);

        // Whether to show the middle hole
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleRadius(80f);//Set the size of the middle hole
        // translucent circle
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setTransparentCircleColor(Color.WHITE); //Set the color of the translucent circle
        pieChart.setTransparentCircleAlpha(125); //Set the transparency of the semi-transparent circle

        //You can add text in the middle of the pie chart
        /*pieChart.setDrawCenterText(true);
        pieChart.setCenterText("Total Employee"); //Set the middle text
        pieChart.setCenterTextColor(R.color.purple_500); //The color of the middle question
        pieChart.setCenterTextSizePixels(24);  //The size of the middle text px
        //pieChart.setCenterTextRadiusPercent(1f);
        pieChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD); //The style of the middle text*/
        //pieChart.setCenterTextOffset(0, 0); //Offset of middle text

        pieChart.setRotationAngle(0);// Initial rotation angle
        pieChart.setRotationEnabled(true);// Can be rotated manually
        pieChart.setUsePercentValues(true);//Displayed as a percentage
        pieChart.getDescription().setEnabled(false); //Cancel the description in the lower right corner

        //Whether to display the text description of each part
        pieChart.setDrawEntryLabels(false);
        pieChart.setEntryLabelColor(Color.RED); //The color of the description text
        pieChart.setEntryLabelTextSize(14);//Description text size
        pieChart.setEntryLabelTypeface(Typeface.SANS_SERIF); //Description style

        //The offset of the figure relative to the up, down, left and right
        //pieChart.setExtraOffsets(20, 8, 75, 8);
        pieChart.setExtraOffsets(5, 5, 5, 5);

        //The background color of the icon
        pieChart.setBackgroundColor(Color.TRANSPARENT);

        // Set the friction coefficient of pieChart chart rotation resistance [0,1]
        pieChart.setDragDecelerationFrictionCoef(0.75f);

        Legend legend = pieChart.getLegend();
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);  //Set the legend horizontal display
        //legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); //top
        //legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); //Right to it

        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setXEntrySpace(7f);//x axis spacing
        legend.setYEntrySpace(10f); //Y axis spacing
        legend.setYOffset(10f);  //Y offset of the legend
        legend.setXOffset(10f);  //Offset of legend x
        legend.setTextColor(Color.parseColor("#a1a1a1")); //The color of the legend text
        legend.setTextSize(13);  //The size of the legend text

    }

    /**
     * Display solid circle
     *
     * @param yvals
     * @param colors
     */

    public void showSolidPieChart(List<PieEntry> yvals, List<Integer> colors) {

        //Data collection
        PieDataSet dataset = new PieDataSet(yvals, "");

        dataset.setValueLinePart1Length(0.6f);
        dataset.setValueLinePart2Length(0.3f);
        dataset.setValueLineWidth(2f);
        dataset.setValueLinePart1OffsetPercentage(115f);

        //Fill the color of each area
        dataset.setColors(colors);
        //Whether to display the value on the graph
        dataset.setDrawValues(true);

        dataset.setValueTextSize(14);
        dataset.setValueTextColor(Color.RED);

        dataset.setValueTypeface(Typeface.DEFAULT_BOLD);

        // When the value position is the outer edge, it means the length of the first half of the line.
        dataset.setValueLinePart1Length(0.4f);

        // When the value position is the outer line, it indicates the length of the second half of the line.
        dataset.setValueLinePart2Length(0.4f);

        // When ValuePosits is OutsiDice, the offset is indicated as a percentage of the slice size
        dataset.setValueLinePart1OffsetPercentage(80f);

        // When the value position is the outer line, it indicates the color of the line.
        dataset.setValueLineColor(Color.parseColor("#a1a1a1"));

        // Set the position of Y value inside or outside the circle
        dataset.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        // Set the color of the Y axis description line and the filled area to be the same
        //dataset.setUsingSliceColorAsValueLineColor(false);
        // Set the gap before each
        dataset.setSliceSpace(0);

        //Set the distance that changes when the pie item is selected
        dataset.setSelectionShift(5f);
        //Data input
        PieData pieData = new PieData(dataset);

        // The formatted data is%
        pieData.setValueFormatter(new PercentFormatter());

        // show attempt
        pieChart.setData(pieData);
    }


    /**
     * Display ring
     *
     * @param yvals
     * @param colors
     */
    public void showRingPieChart(List<PieEntry> yvals, List<Integer> colors) {
        //Show as ring
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(70f);//Set the size of the middle hole
        pieChart.setRotationEnabled(false);
        pieChart.setRotationAngle(70f);

        //highlightValue(float x, int dataSetIndex, boolean callListener)
        //pieChart.highlightValue(, yvals.get(0), false);

        // highlight the entry and x-position 50 in the first (0) DataSet
/*        Highlight highlight = new Highlight(50f, 50f, yvals.indexOf(0));

        pieChart.highlightValue(highlight, false); // highlight this value, don't call listener*/


        //Data collection
        PieDataSet dataSet = new PieDataSet(yvals, "");


        //Fill the color of each area
        dataSet.setColors(colors);
        //Whether to display the value on the graph
        dataSet.setDrawValues(true);

        dataSet.setValueTextSize(14);

        dataSet.setValueTextColor(Color.BLACK);

        // Style of text
        dataSet.setValueTypeface(Typeface.DEFAULT_BOLD);

        // When the value position is the outer edge, it means the length of the first half of the line.
        dataSet.setValueLinePart1Length(0.6f);

        // When the value position is the outer line, it indicates the length of the second half of the line.
        dataSet.setValueLinePart2Length(0.6f);

        dataSet.setValueLineWidth(2f);

        // When the value position is the outer line, it indicates the color of the line.
        dataSet.setValueLineColor(Color.parseColor("#CCCCCC"));

        dataSet.setValueLinePart1OffsetPercentage(135f);  // Line starts outside of chart
        // Set the position of Y value inside or outside the circle


        //dataSet.setValueLineVariableLength(true);

        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        // Set the color of the Y axis description line and the filled area to be the same
        //dataSet.setUsingSliceColorAsValueLineColor(false);
        // Set the gap before each
        dataSet.setSliceSpace(0);

        //Set the distance that changes when the pie item is selected
        dataSet.setSelectionShift(5f);
        dataSet.setHighlightEnabled(true);
        /***********************************************************************ToStart******/

        //Data input
        PieData pieData = new PieData(dataSet);
        // The formatted data is%
        pieData.setValueFormatter(new DecimalRemover(new DecimalFormat("###,###,###")));
        // show attempt
        pieChart.setData(pieData);

    }


}