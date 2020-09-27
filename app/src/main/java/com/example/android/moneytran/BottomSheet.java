package com.example.android.moneytran;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.example.android.moneytran.databinding.BottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheet extends BottomSheetDialogFragment {
    private BottomSheetBehavior bottomSheetBehavior;
    private BottomSheetBinding binding;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog bottomSheet = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View rootView = View.inflate(getContext(), R.layout.bottom_sheet, null);
        binding = DataBindingUtil.bind(rootView);
        bottomSheet.setContentView(rootView);
        bottomSheetBehavior = BottomSheetBehavior.from((View) (rootView.getParent()));

        //setting Peek at the 16:9 ratio keyline of its parent.
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);

        //setting height of bottom sheet for expanded state
        binding.extraSpace.setMinimumHeight((Resources.getSystem().getDisplayMetrics().heightPixels) / 3);

        binding.expandedStateClose.setOnClickListener( view -> {
            dismiss();
        });

        binding.expandedStateBar.setVisibility(View.GONE);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (BottomSheetBehavior.STATE_EXPANDED == i) {
                    binding.expandedStateBar.setVisibility(View.VISIBLE);
                    binding.collapsedStateBar.setVisibility(View.GONE);
                }
                if (BottomSheetBehavior.STATE_COLLAPSED == i) {
                    binding.collapsedStateBar.setVisibility(View.VISIBLE);
                    binding.expandedStateBar.setVisibility(View.GONE);
                }

                if (BottomSheetBehavior.STATE_HIDDEN == i) {
                    dismiss();
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

        return bottomSheet;
    }

    @Override
    public void onStart() {
        super.onStart();
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @Override public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }


}
