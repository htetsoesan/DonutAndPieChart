package com.example.myapplication.viewholders;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ViewHolderHorizontalChartBinding;

public class HorizontalChartViewHolder extends RecyclerView.ViewHolder {

    private final ViewHolderHorizontalChartBinding mBinding;

    public HorizontalChartViewHolder(@NonNull ViewHolderHorizontalChartBinding binding) {
        super(binding.getRoot());
        this.mBinding = binding;

        mBinding.getRoot().setOnClickListener(view -> {

            //  mBinding.getRoot().setAnimation(mBinding.getRoot().getResources().getAnimation(R.anim.item_animation_left_to_right));
            Toast.makeText(itemView.getContext(), "item clicking", Toast.LENGTH_SHORT).show();
        });
    }
}
