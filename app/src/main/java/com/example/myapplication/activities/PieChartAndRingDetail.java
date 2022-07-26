package com.example.myapplication.activities;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.PieChartManager;
import com.example.myapplication.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class PieChartAndRingDetail extends AppCompatActivity {
    boolean temp = false;
    private PieChart pieChart, pie_chat2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_and_ring_detail);

        initView();
    }

    private void initView() {
        pieChart = findViewById(R.id.pie_chat1);
        pie_chat2 = findViewById(R.id.pie_chat2);

        temp = true;

        //showhodlePieChart();

        showRingPieChart();
    }

    private void showhodlePieChart() {
        // Set the number of each share
        List<PieEntry> yvals = new ArrayList<>();
        yvals.add(new PieEntry(2.0f, "Han"));
        yvals.add(new PieEntry(3.0f, "Hui"));
        yvals.add(new PieEntry(4.0f, "Zhuang"));
        yvals.add(new PieEntry(5.0f, "Uighur"));
        yvals.add(new PieEntry(6.0f, "Tujia"));
        //Set the color of each copy
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#6785f2"));
        colors.add(Color.parseColor("#675cf2"));
        colors.add(Color.parseColor("#496cef"));
        colors.add(Color.parseColor("#aa63fa"));
        colors.add(Color.parseColor("#58a9f5"));


        PieChartManager pieChartManager = new PieChartManager(pieChart, temp);
        pieChartManager.showSolidPieChart(yvals, colors);
    }

    private void showRingPieChart() {
        //Set the number of each share
        List<PieEntry> yvals = new ArrayList<>();
        yvals.add(new PieEntry(3.0f, "master's degree"));
        yvals.add(new PieEntry(4.0f, "Doctor"));

        // Set the color of each copy
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#6785f2"));
        colors.add(Color.parseColor("#675cf2"));

        PieChartManager pieChartManager = new PieChartManager(pie_chat2, temp);
        pieChartManager.showRingPieChart(yvals, colors);
    }

}