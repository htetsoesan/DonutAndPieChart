package com.example.myapplication.adapters;

import android.annotation.SuppressLint;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ViewHolderHorizontalChartBinding;
import com.example.myapplication.viewholders.HorizontalChartViewHolder;

import java.util.ArrayList;

import kotlin.math.MathKt;

public class HorizontalChartAdapter extends RecyclerView.Adapter<HorizontalChartViewHolder> {

    private static final long FADE_DURATION = 1000;
    private final ArrayList<Integer> mPointList;
    private final int mMaxValueOnXAxis;
    private final float marginInDp = 16f;
    ViewHolderHorizontalChartBinding binding;
    private int viewWidth = 0;
    private int parentWidth = 0;
    private float marginInPx = 0f;

    public HorizontalChartAdapter(ArrayList<Integer> pointList, int maxValueOnXAxis) {
        this.mPointList = pointList;
        this.mMaxValueOnXAxis = maxValueOnXAxis;
    }

    @NonNull
    @Override
    public HorizontalChartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.view_holder_horizontal_chart, parent, false);
        parentWidth = getParentWidth(parent);
        marginInPx = ConvertMarginInPxFromDp(parent);
        return new HorizontalChartViewHolder(binding);
    }

    private float ConvertMarginInPxFromDp(ViewGroup parent) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, marginInDp, parent.getContext().getResources().getDisplayMetrics());
    }

    private int getParentWidth(ViewGroup parent) {
        int roundValue = MathKt.roundToInt(parent.getContext().getResources().getDisplayMetrics().widthPixels - (marginInPx * 2));
        return roundValue;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HorizontalChartViewHolder holder, int position) {

        viewWidth = calculateViewWidth(mPointList.get(position));
        //binding the text to the textview
        binding.valueText.setText(mPointList.get(position).toString());
        binding.view.getLayoutParams().width = viewWidth;

        setFadeAnimation(holder.itemView);
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    private int calculateViewWidth(Integer value) {
        return value * parentWidth / mMaxValueOnXAxis;
    }

    @Override
    public int getItemCount() {
        return mPointList.size();
    }
}
