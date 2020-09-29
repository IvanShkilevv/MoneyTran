package com.example.android.moneytran;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.example.android.moneytran.currency.BritishPound;
import com.example.android.moneytran.currency.ChineseYuan;
import com.example.android.moneytran.currency.CurrentCurrency;
import com.example.android.moneytran.currency.Euro;
import com.example.android.moneytran.currency.JapaneseYen;
import com.example.android.moneytran.currency.RussianRuble;
import com.example.android.moneytran.currency.UkrainianHryvnia;
import com.example.android.moneytran.currency.UnitedStatesDollar;
import com.example.android.moneytran.databinding.BottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/*
29.09.2020
1) For BottomSheet could be used another approach: setting RecyclerView  and custom RadioGroup,
 worth considering just in case of adding a lot of currencies.
 2) title "Choose currency" moves badly (when BottomSheet changes state). Developed according to the design red lines.
 */
public class BottomSheet extends BottomSheetDialogFragment {
    private BottomSheetBehavior bottomSheetBehavior;
    private BottomSheetBinding binding;
    onCurrencyChangedListener eventListener;

    public interface onCurrencyChangedListener {
        void currentCurrencyChanged();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            eventListener = (onCurrencyChangedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement onCurrencyChangedListener");
        }
    }

    @NonNull
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

        binding.expandedStateClose.setOnClickListener(view -> dismiss());

        binding.expandedStateBar.setVisibility(View.GONE);
        setCurrenciesUnchecked();
        setCurrentCurrencyChecked();
        setCardViewsListeners();

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

    @Override
    public void onPause() {
        super.onPause();
        eventListener.currentCurrencyChanged();
    }

    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }

    private void setCurrenciesUnchecked() {
        binding.imageViewCheckEUR.setVisibility(View.GONE);
        binding.imageViewCheckUSD.setVisibility(View.GONE);
        binding.imageViewCheckGBP.setVisibility(View.GONE);
        binding.imageViewCheckRUB.setVisibility(View.GONE);
        binding.imageViewCheckCNY.setVisibility(View.GONE);
        binding.imageViewCheckJPY.setVisibility(View.GONE);
        binding.imageViewCheckUAH.setVisibility(View.GONE);
    }

    private void setCurrentCurrencyChecked() {
        if (CurrentCurrency.currentCurrency instanceof Euro) {
            binding.imageViewCheckEUR.setVisibility(View.VISIBLE);
        }

        if (CurrentCurrency.currentCurrency instanceof UnitedStatesDollar) {
            binding.imageViewCheckUSD.setVisibility(View.VISIBLE);
        }

        if (CurrentCurrency.currentCurrency instanceof BritishPound) {
            binding.imageViewCheckGBP.setVisibility(View.VISIBLE);
        }

        if (CurrentCurrency.currentCurrency instanceof RussianRuble) {
            binding.imageViewCheckRUB.setVisibility(View.VISIBLE);
        }

        if (CurrentCurrency.currentCurrency instanceof ChineseYuan) {
            binding.imageViewCheckCNY.setVisibility(View.VISIBLE);
        }

        if (CurrentCurrency.currentCurrency instanceof JapaneseYen) {
            binding.imageViewCheckJPY.setVisibility(View.VISIBLE);
        }

        if (CurrentCurrency.currentCurrency instanceof UkrainianHryvnia) {
            binding.imageViewCheckUAH.setVisibility(View.VISIBLE);
        }

    }

    private void setCardViewsListeners() {
        binding.cardViewCurrencyEUR.setOnClickListener(v -> {
            setCurrenciesUnchecked();
            CurrentCurrency.currentCurrency = new Euro();
            setCurrentCurrencyChecked();
        });

        binding.cardViewCurrencyUSD.setOnClickListener(v -> {
            setCurrenciesUnchecked();
            CurrentCurrency.currentCurrency = new UnitedStatesDollar();
            setCurrentCurrencyChecked();
        });

        binding.cardViewCurrencyGBP.setOnClickListener(v -> {
            setCurrenciesUnchecked();
            CurrentCurrency.currentCurrency = new BritishPound();
            setCurrentCurrencyChecked();
        });

        binding.cardViewCurrencyRUB.setOnClickListener(v -> {
            setCurrenciesUnchecked();
            CurrentCurrency.currentCurrency = new RussianRuble();
            setCurrentCurrencyChecked();
        });

        binding.cardViewCurrencyCNY.setOnClickListener(v -> {
            setCurrenciesUnchecked();
            CurrentCurrency.currentCurrency = new ChineseYuan();
            setCurrentCurrencyChecked();
        });

        binding.cardViewCurrencyJPY.setOnClickListener(v -> {
            setCurrenciesUnchecked();
            CurrentCurrency.currentCurrency = new JapaneseYen();
            setCurrentCurrencyChecked();
        });

        binding.cardViewCurrencyUAH.setOnClickListener(v -> {
            setCurrenciesUnchecked();
            CurrentCurrency.currentCurrency = new UkrainianHryvnia();
            setCurrentCurrencyChecked();
        });

    }

}
