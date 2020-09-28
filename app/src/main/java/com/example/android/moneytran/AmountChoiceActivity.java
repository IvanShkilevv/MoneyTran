package com.example.android.moneytran;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.android.moneytran.currency.CurrentCurrency;
import com.example.android.moneytran.databinding.ActivityAmountChoiceBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
            Intent intent = new Intent();
            intent.putExtra("editTextValue", "value");
            setResult(RESULT_OK, intent);
            finish();
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
                    if (checkInputValidation()) {
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

    private boolean checkInputValidation() {
        boolean isInputValid = false;
        CharSequence s = binding.editText.getText();

        if (s.length() > 1 & s.charAt(0) == currencySymbol) {
            isInputValid = true;
        }

        if (s.charAt(1) == '.') {
            isInputValid = false;
        }

        if (s.charAt(1) == '0' & s.length() == 2) {
            isInputValid = false;
        }

        if (s.charAt(1) == '0'& s.charAt(2) == '.' & s.length() == 3) {
            isInputValid = false;
        }

        if (s.charAt(1) == '0' & s.charAt(2) == '0') {
            isInputValid = false;
        }

//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) == '.' ) {
//                try {
//                    char q = s.charAt(i+3);
//                } catch (IndexOutOfBoundsException e) {
//                    isInputValid = false;
//                }
//            }
//        }

        return isInputValid;
    }



//    private float getUserInput () {
//        String currentText = binding.editText.getText().toString();
//
//        return ;
//    }




}