package com.example.android.moneytran;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.android.moneytran.currency.CurrentCurrency;
import com.example.android.moneytran.databinding.ActivityAmountChoiceBinding;

import java.text.DecimalFormat;
import java.util.Locale;

public class AmountChoiceActivity extends AppCompatActivity {
    private static final String LOG_TAG = AmountChoiceActivity.class.getSimpleName();
    ActivityAmountChoiceBinding binding;
    private final char currencySymbol = CurrentCurrency.currentCurrency.getSymbol();
    private float amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAmountChoiceBinding.inflate(getLayoutInflater());
        View rootView = binding.getRoot();
        setContentView(rootView);

        //setting Locale for correct handling float values (sets dot as separator)
        Locale.setDefault(Locale.US);

        binding.doneButton.setVisibility(View.GONE);
        setCloseActivityListeners();
        setEditTextProperties();
        setTextChangedListener();

        binding.doneButton.setOnClickListener(v -> {
            if (parseInputText() & inputValueNotNull()) {
                Intent intent = new Intent();
                intent.putExtra("editTextValue", amount);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    private void setCloseActivityListeners() {
        binding.toolbarClose.setOnClickListener(v -> finish());
        binding.cancelButton.setOnClickListener(v -> finish());
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
                if (checkInputValidation(s)) {
                    binding.cancelButton.setVisibility(View.GONE);
                    binding.doneButton.setVisibility(View.VISIBLE);
                } else {
                    binding.cancelButton.setVisibility(View.VISIBLE);
                    binding.doneButton.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean checkInputValidation(CharSequence s) {
        boolean isInputValid = false;
        try {
            if (s.length() > 1 & s.charAt(0) == currencySymbol) {
                isInputValid = true;
            }

            if (s.charAt(1) == '.') {
                isInputValid = false;
            }

            if (s.charAt(1) == '0' & s.length() == 2) {
                isInputValid = false;
            }

            if (s.charAt(1) == '0' & s.charAt(2) == '0') {
                isInputValid = false;
            }

            if (s.charAt(1) == '0' & s.charAt(2) == '.' & s.length() == 3) {
                isInputValid = false;
            }

            if (s.charAt(1) == '0' & s.charAt(2) == '.' & s.charAt(3) == '0' & s.length() == 4) {
                isInputValid = false;
            }

        } catch (IndexOutOfBoundsException e) {
            Log.e(LOG_TAG, e.toString());
        }

        return isInputValid;
    }

    private boolean parseInputText() {
        boolean isParsingSuccessful = false;
        String text = binding.editText.getText().toString();
        try {
            float rawAmount = Float.parseFloat(text.substring(1));
            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
            amount = Float.parseFloat(decimalFormat.format(rawAmount));
            isParsingSuccessful = true;
        } catch (NumberFormatException e) {
            Log.e(LOG_TAG, e.toString());
            Toast.makeText(this, R.string.wrong_user_input, Toast.LENGTH_SHORT).show();
        } catch (NullPointerException e) {
            Log.e(LOG_TAG, e.toString());
            Toast.makeText(this, R.string.empty_user_input, Toast.LENGTH_SHORT).show();
        }
        return isParsingSuccessful;
    }

    private boolean inputValueNotNull() {
        if (amount != 0) {
            return true;
        } else {
            Toast.makeText(this, R.string.empty_user_input, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}