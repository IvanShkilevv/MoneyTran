package com.example.android.moneytran;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.android.moneytran.currency.CurrentCurrency;
import com.example.android.moneytran.databinding.ActivityAmountChoiceBinding;


public class AmountChoiceActivity extends AppCompatActivity {
    private static final String LOG_TAG = AmountChoiceActivity.class.getSimpleName();
    ActivityAmountChoiceBinding binding;
    private final char currencySymbol = CurrentCurrency.currentCurrency.getSymbol();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAmountChoiceBinding.inflate(getLayoutInflater());
        View rootView = binding.getRoot();
        setContentView(rootView);

        binding.doneButton.setVisibility(View.GONE);
        setCloseActivityListeners();
        setTextChangedListener();
        setEditTextProperties();

        binding.doneButton.setOnClickListener(v -> {

        });


    }

    private void setCloseActivityListeners() {
        binding.toolbarClose.setOnClickListener(v -> {
            finish();
        });

        binding.cancelButton.setOnClickListener(v -> {
            finish();
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setEditTextProperties() {
        String emptyAmount = currencySymbol + "0.00";
        binding.editText.setHint(emptyAmount);
        binding.editText.setOnTouchListener((v, event) -> {
            setCurrencySymbol();
            binding.editText.getBackground().setColorFilter(getResources()
                    .getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            return false;
        });
    }

    private void setCurrencySymbol() {
        String currentText = binding.editText.getText().toString();
        String strCurrencySymbol = currencySymbol + "";
        if (!currentText.contains(strCurrencySymbol)) {
            String inputText = strCurrencySymbol + currentText;
            binding.editText.setText(inputText);
        }
    }

    private void setTextChangedListener() {
        binding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setCurrencySymbol();
                try {
                    if (s.length() > 1 ) {
                        binding.cancelButton.setVisibility(View.GONE);
                        binding.doneButton.setVisibility(View.VISIBLE);
                    }
                    else {
                        binding.cancelButton.setVisibility(View.VISIBLE);
                        binding.doneButton.setVisibility(View.GONE);
                    }
                } catch (IndexOutOfBoundsException e) {
                   Log.e(LOG_TAG, e.toString()) ;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }




}