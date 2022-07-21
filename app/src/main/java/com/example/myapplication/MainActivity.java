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
        mChart.setCenterTextSize(12);
        mChart.setCenterTextColor(getColor(R.color.purple_500));

        // setting sample Data for Pie Chart
        setDataForPieChart();

        setUpClickListener();
    }

    public void setDataForPieChart() {
        ArrayList<Entry> yVals = new ArrayList<>();

        for (int i = 0; i < yValues.length; i++)
            yVals.add(new Entry(yValues[i], i));

        ArrayList<String> xVals = new ArrayList<>();

        Collections.addAll(xVals, xValues);

        // create pieDataSet
        PieDataSet dataSet = new PieDataSet(yVals, "");
        dataSet.setSliceSpace(0f);
        dataSet.setSelectionShift(3);

        // adding colors
        ArrayList<Integer> colors = new ArrayList<>();

        // add custom colors
        for (int c : MY_COLORS)
            colors.add(c);

        // set color to data set
        dataSet.setColors(colors);

        ArrayList<String> temp = new ArrayList<>();

        String legendValue = "";
        for (int i = 0; i < xVals.size(); i++) {
            for (int j = 0; j < yVals.size(); j++) {
                legendValue = xValues[i] + "    " + yValues[i];
            }
            temp.add(legendValue);
        }

        //  create pie data object and set xValues and yValues and set it to the pieChart
        PieData data = new PieData(temp, dataSet);

        data.setValueFormatter(new DecimalRemover(new DecimalFormat("###,###,###")));
        data.setValueTextSize(10);
        data.setValueTextColor(Color.WHITE);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        // refresh/update pie chart
        mChart.invalidate();

        // animate pie chart
        mChart.animateXY(1400, 1400);

        mChart.setUsePercentValues(true);

        // for not drawing the x-values
        mChart.setDrawSliceText(false);

        Legend mChartLegend = mChart.getLegend();
        mChartLegend.setFormSize(12);
        mChartLegend.setTextSize(14f);
        mChartLegend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);

        mChartLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);

        // set what type of form/shape should be used
        mChartLegend.setForm(Legend.LegendForm.CIRCLE);

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