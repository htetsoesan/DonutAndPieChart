package com.example.myapplication.viewholders;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ViewHolderHorizontalBarChartBinding;


public class HorizontalBarChartViewHolder extends RecyclerView.ViewHolder {

    private final ViewHolderHorizontalBarChartBinding mBinding;

    public HorizontalBarChartViewHolder(@NonNull ViewHolderHorizontalBarChartBinding binding) {
        super(binding.getRoot());
        this.mBinding = binding;

        mBinding.getRoot().setOnClickListener(view -> {

            //  mBinding.getRoot().setAnimation(mBinding.getRoot().getResources().getAnimation(R.anim.item_animation_left_to_right));
            Toast.makeText(itemView.getContext(), "item clicking", Toast.LENGTH_SHORT).show();
        });
    }

    public void clearAnimation() {
        mBinding.getRoot().clearAnimation();
    }
}
