package com.example.android.moneytran;

import android.app.Dialog;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        View view = View.inflate(getContext(), R.layout.bottom_sheet, null);
        binding = DataBindingUtil.bind(view);
        bottomSheet.setContentView(view);

        bottomSheetBehavior = BottomSheetBehavior.from((View) (view.getParent()));

        //setting Peek at the 16:9 ratio keyline of its parent.
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);

        //setting  height of bottom sheet
        binding.extraSpace.setMinimumHeight((Resources.getSystem().getDisplayMetrics().heightPixels) / 3);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (BottomSheetBehavior.STATE_EXPANDED == i) {
//                    showView(binding.toolbar, getActionBarSize());
//                    hideAppBar(binding.profileLayout);

                    Toast.makeText(getActivity().getApplicationContext(), "STATE_EXPANDED", Toast.LENGTH_SHORT).show();
                }
                if (BottomSheetBehavior.STATE_COLLAPSED == i) {
//                    hideAppBar(binding.toolbar);
//                    showView(binding.profileLayout, getActionBarSize());
                    Toast.makeText(getActivity().getApplicationContext(), "STATE_COLLAPSED", Toast.LENGTH_SHORT).show();

                }

                if (BottomSheetBehavior.STATE_HIDDEN == i) {
                    Toast.makeText(getActivity().getApplicationContext(), "STATE_HIDDEN", Toast.LENGTH_SHORT).show();

                    dismiss();
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

//        binding.cancelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                dismiss();
//            }
//        });
//
//        //hiding app bar at the start
//        hideAppBar(binding.appBarLayout);
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


    private void hideAppBar(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = 0;
        view.setLayoutParams(params);
    }

    private void showView(View view, int size) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = size;
        view.setLayoutParams(params);
    }

//    private int getActionBarSize() {
//        final TypedArray array = getContext().getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
//        int size = (int) array.getDimension(0, 0);
//        return size;
//    }


}
