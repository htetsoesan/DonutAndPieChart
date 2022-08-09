package com.example.myapplication.adapters;

import android.annotation.SuppressLint;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ViewHolderHorizontalBarChartBinding;
import com.example.myapplication.viewholders.HorizontalBarChartViewHolder;

import java.util.ArrayList;

import kotlin.math.MathKt;

public class HorizontalBarChartAdapter extends RecyclerView.Adapter<HorizontalBarChartViewHolder> {

    private static final long FADE_DURATION = 1000;
    private final ArrayList<Integer> mPointList;
    private final int mMaxValueOnXAxis;
    private final float marginInDp = 16f;
    protected int mLastPosition = -1;
    ViewHolderHorizontalBarChartBinding mBinding;
    private int viewWidth = 0;
    private int parentWidth = 0;
    private float marginInPx = 0f;

    public HorizontalBarChartAdapter(ArrayList<Integer> pointList, int maxValueOnXAxis) {
        this.mPointList = pointList;
        this.mMaxValueOnXAxis = maxValueOnXAxis;
    }

    @NonNull
    @Override
    public HorizontalBarChartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.view_holder_horizontal_bar_chart, parent, false);
        parentWidth = getParentWidth(parent);
        marginInPx = ConvertMarginInPxFromDp(parent);
        return new HorizontalBarChartViewHolder(mBinding);
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
    public void onBindViewHolder(@NonNull HorizontalBarChartViewHolder holder, int position) {

        viewWidth = calculateViewWidth(mPointList.get(position));
        //binding the text to the textview
        mBinding.valueText.setText(mPointList.get(position).toString());
        mBinding.view.getLayoutParams().width = viewWidth;
        setAnimation(holder.itemView, position);
    }

    private void setAnimation(View view, int position) {

        Log.d("TAG", "setAnimation: " + mLastPosition);
        if (position > mLastPosition) {
            Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.item_animation_left_to_right);
            view.startAnimation(animation);
            mLastPosition = position;
        }

    }

    private int calculateViewWidth(Integer value) {
        return value * parentWidth / mMaxValueOnXAxis;
    }

    private void setZoomInAnimation(View view) {
        Animation zoomIn = AnimationUtils.loadAnimation(view.getContext(), R.anim.item_animation_left_to_right);// animation file
        view.startAnimation(zoomIn);
    }

    @Override
    public int getItemCount() {
        return mPointList.size();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull HorizontalBarChartViewHolder holder) {
        ((HorizontalBarChartViewHolder) holder).clearAnimation();
    }
}
