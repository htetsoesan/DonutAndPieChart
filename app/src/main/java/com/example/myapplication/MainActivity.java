package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    PieChart mChart;
    private final int[] yValues = {100, 40};
    private final String[] xValues = {"Men", "Women"};

    public static final int[] MY_COLORS = {
            Color.rgb(20, 122, 214),
            Color.rgb(235, 101, 101)

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChart = findViewById(R.id.piechart);
        mChart.setDescription("");

        mChart.setRotationEnabled(true);

        int tempInt = 0;
        for (int yValue : yValues) {
            tempInt += yValue;
        }

        mChart.setCenterText(tempInt + "\n" + getString(R.string.lbl_employees));
        mChart.setCenterTextSize(14);
        mChart.setCenterTextColor(getColor(R.color.purple_500));

        setUpClickListener();

        // setting sample Data for Pie Chart
        setDataForPieChart();

    }

    public void setDataForPieChart() {
        ArrayList<Entry> yVals1 = new ArrayList<>();

        for (int i = 0; i < yValues.length; i++)
            yVals1.add(new Entry(yValues[i], i));

        ArrayList<String> xVals = new ArrayList<>();

        Collections.addAll(xVals, xValues);

        // create pieDataSet
        PieDataSet dataSet = new PieDataSet(yVals1, "");
        dataSet.setSliceSpace(0f);
        dataSet.setSelectionShift(3);

        // adding colors
        ArrayList<Integer> colors = new ArrayList<>();

        // Added My Own colors
        for (int c : MY_COLORS)
            colors.add(c);


        dataSet.setColors(colors);

        //  create pie data object and set xValues and yValues and set it to the pieChart
        PieData data = new PieData(xVals, dataSet);

        //data.setValueFormatter(new PercentFormatter());

        data.setValueFormatter(new DecimalRemover(new DecimalFormat("###,###,###")));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.WHITE);

        //For not drawing the entry-values
        //data.setDrawValues(false);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);


        // refresh/update pie chart
        mChart.invalidate();

        // animate piechart
        mChart.animateXY(1400, 1400);

        mChart.setUsePercentValues(true);

        //For not drawing the x-values
        mChart.setDrawSliceText(false);

        Legend mChartLegend = mChart.getLegend();
        mChartLegend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);


        mChartLegend.setForm(Legend.LegendForm.CIRCLE);
        mChartLegend.setFormSize(12);
        mChartLegend.setTextSize(14f);

        // Legends to show on bottom of the graph
/*        Legend mChartLegend = mChart.getLegend();
        mChartLegend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);

        mChartLegend.setXEntrySpace(7);
        mChartLegend.setYEntrySpace(8);
        mChartLegend.setTextSize(12);
        mChartLegend.setFormSize(10);
        mChartLegend.setFormToTextSpace(2);
        mChartLegend.setWordWrapEnabled(true);*/

        //legend attributes
/*
        mChartLegend.setForm(Legend.LegendForm.CIRCLE);
        mChartLegend.setTextSize(12);
        mChartLegend.setFormSize(20);
        mChartLegend.setFormToTextSpace(2);
        //to wrap legend text
        mChartLegend.setWordWrapEnabled(true);
        Log.d("legend " ,mChartLegend.getEntries().toString());*/
    }


    private void setUpClickListener() {
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                // display msg when value selected
                if (e == null)
                    return;

                Toast.makeText(MainActivity.this, xValues[e.getXIndex()] + " is " + e.getVal() + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }
}