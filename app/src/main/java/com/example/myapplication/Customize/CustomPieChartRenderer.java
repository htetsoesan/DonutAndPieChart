package com.example.myapplication.Customize;

import android.graphics.Canvas;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.renderer.PieChartRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.ViewPortHandler;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CustomPieChartRenderer extends PieChartRenderer {

    private final float mCircleRadius;
    boolean mTemp;

    public CustomPieChartRenderer(PieChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler, boolean temp, float circleRadius) {
        super(chart, animator, viewPortHandler);
        this.mTemp = temp;
        this.mCircleRadius = circleRadius;
    }

    public void drawValues(@NotNull Canvas c) {
        super.drawValues(c);

        MPPointF center = mChart.getCenterCircleBox();

        float radius = mChart.getRadius();

        float rotationAngle = mChart.getRotationAngle();

        float[] drawAngles = mChart.getDrawAngles();

        float[] absoluteAngles = mChart.getAbsoluteAngles();
        ChartAnimator var31 = this.mAnimator;

        float phaseX = var31.getPhaseX();

        float phaseY = var31.getPhaseY();

        float roundedRadius = (radius - radius * mChart.getHoleRadius() / 100.0F) / 2.0F;

        float holeRadiusPercent = mChart.getHoleRadius() / 100.0F;
        float labelRadiusOffset = radius / 10.0F * 3.6F;

        if (mChart.isDrawHoleEnabled()) {
            labelRadiusOffset = (radius - radius * holeRadiusPercent) / 2.0F;

            if (!mChart.isDrawSlicesUnderHoleEnabled()) {

                if (mChart.isDrawRoundedSlicesEnabled()) {
                    rotationAngle += roundedRadius * (float) 360 / (float) (6.283185307179586D * (double) radius);
                }
            }
        }

        float labelRadius = radius - labelRadiusOffset;

        PieData pieData = mChart.getData();

        List<IPieDataSet> dataSets = pieData.getDataSets();
        float angle;
        int xIndex = 0;
        c.save();
        int i = 0;

        for (int var17 = dataSets.size(); i < var17; ++i) {
            IPieDataSet dataSet = dataSets.get(i);
            float sliceSpace = this.getSliceSpace(dataSet);
            int var20 = 0;

            for (int var21 = dataSet.getEntryCount(); var20 < var21; ++var20) {
                angle = xIndex == 0 ? 0.0F : absoluteAngles[xIndex - 1] * phaseX;
                float sliceAngle = drawAngles[xIndex];
                float sliceSpaceMiddleAngle = sliceSpace / (0.017453292F * labelRadius);
                angle += (sliceAngle - sliceSpaceMiddleAngle / 2.0F) / 2.0F;
                if (dataSet.getValueLineColor() != 1122867) {
                    float transformedAngle = rotationAngle + angle * phaseY;
                    double var26 = (double) transformedAngle * (double) 0.017453292F;
                    float sliceXBase = (float) Math.cos(var26);
                    double var27 = (double) transformedAngle * (double) 0.017453292F;
                    float sliceYBase = (float) Math.sin(var27);
                    float valueLinePart1OffsetPercentage = dataSet.getValueLinePart1OffsetPercentage() / 100.0F;
                    float line1Radius = mChart.isDrawHoleEnabled() ? (radius - radius * holeRadiusPercent) * valueLinePart1OffsetPercentage + radius * holeRadiusPercent : radius * valueLinePart1OffsetPercentage;
                    float px = line1Radius * sliceXBase + center.getX();
                    float py = line1Radius * sliceYBase + center.getY();
                    if (mTemp) {
                        mRenderPaint.setColor(dataSet.getColor(var21));
                    }
                    c.drawCircle(px, py, mCircleRadius, mRenderPaint);
                }
                ++xIndex;
            }
        }

        MPPointF.recycleInstance(center);
        c.restore();
    }
}
