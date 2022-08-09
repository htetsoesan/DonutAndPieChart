package com.example.myapplication.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.adapters.HorizontalChartAdapter;
import com.example.myapplication.databinding.ActivityScrollingBarChartBinding;

import java.util.ArrayList;
import java.util.Collections;

public class ScrollingHorizontalBarChartActivity extends AppCompatActivity {

    ActivityScrollingBarChartBinding mBinding;
    ArrayList<Integer> pointList = new ArrayList<>();
    private int maxValueOnXAxis = 0;
    private int maxValue = 0;
    private int granularity = 0;
    private HorizontalChartAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityScrollingBarChartBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setDummyData();
        getGranularity();
        addTextViewForXAxis(maxValue);
        setGraphRecyclerView();
    }

    private void setGraphRecyclerView() {
        mAdapter = new HorizontalChartAdapter(pointList, maxValueOnXAxis);
        mBinding.rvHorizontalBarChart.setAdapter(mAdapter);
    }

    private void addTextViewForXAxis(int maxValue) {
        if (maxValue > 10)
            createTextView(5);
        else if (maxValue > 8)
            createTextView(5);
        else if (maxValue > 6)
            createTextView(4);
        else if (maxValue > 4)
            createTextView(3);
        else if (maxValue > 2)
            createTextView(2);
        else
            createTextView(1);
    }

    private void createTextView(int numberOfTextView) {
        generateOriginalLabelTextview();
        for (int i = 1; i <= numberOfTextView; i++) {
            int integerValue = i * granularity;
            generateXAxisTextview(String.valueOf(integerValue));
        }
    }

    private void generateOriginalLabelTextview() {
        TextView textView = new TextView(this);
        textView.setText(R.string.lbl_zero_string);
        mBinding.xAxisTextviewContainerLayout.addView(textView);
    }

    private void generateXAxisTextview(String value) {
        TextView textView = new TextView(this);
        textView.setText(value);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        textView.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
        mBinding.xAxisTextviewContainerLayout.addView(textView);
    }

    private void getGranularity() {
        maxValue = Collections.max(pointList);
        calculateGranularity(maxValue);
    }

    private void calculateGranularity(int maxValue) {
        if (maxValue > 10) {
            maxValueOnXAxis = maxValue + (5 - (maxValue % 5));
            granularity = maxValueOnXAxis / 5;
        } else if (maxValue > 8) {
            maxValueOnXAxis = 10;
            granularity = 2;
        } else if (maxValue > 6) {
            maxValueOnXAxis = 8;
            granularity = 2;
        } else if (maxValue > 4) {
            maxValueOnXAxis = 6;
            granularity = 2;
        } else if (maxValue > 2) {
            maxValueOnXAxis = 4;
            granularity = 2;
        } else if (maxValue > 1) {
            maxValueOnXAxis = 2;
            granularity = 2;
        }

    }

    private void setDummyData() {
        pointList.add(17);

        pointList.add(8);
        pointList.add(12);
        pointList.add(1);
        pointList.add(34);
        pointList.add(50);
        pointList.add(26);
        pointList.add(2);
        pointList.add(4);
        pointList.add(5);
        pointList.add(9);
        pointList.add(7);
        pointList.add(3);
    }
}