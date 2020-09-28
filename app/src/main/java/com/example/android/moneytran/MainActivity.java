package com.example.android.moneytran;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import com.example.android.moneytran.currency.CurrentCurrency;
import com.example.android.moneytran.currency.UnitedStatesDollar;
import com.example.android.moneytran.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity  implements BottomSheet.onCurrencyChangedListener {
    ActivityMainBinding binding;
    private final int requiredRequestCode = 1;
    private float amount;
    private float fee = 0.025F;
    private  String strAmount;
    private  String strFeeAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View rootView = binding.getRoot();
        setContentView(rootView);

        //setting default value
        amount = 0;
        setToolbar();
        setDefaultCurrency();
        setCurrencyChoice();
        setAmountChoice();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateCurrencyUI();
    }

    @Override
    public void currentCurrencyChanged() {
        updateCurrencyUI();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requiredRequestCode) {
            if(resultCode == RESULT_OK) {
                amount = data.getFloatExtra("editTextValue", 0);
                updateAmountUI();
            }
        }
    }

    private void setToolbar() {
        binding.toolbar.setTitle(R.string.send_money_title);
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolbar.setNavigationOnClickListener(view -> finish());
    }

    private void updateCurrencyUI() {
        defineStringAmount();
        binding.imageViewFlagCurrency.setImageResource(CurrentCurrency.currentCurrency.getFlag());
        binding.textViewCurrency.setText(CurrentCurrency.currentCurrency.getName());
        binding.textViewCurrencyBalance.setText(CurrentCurrency.currentCurrency.getBalance());
        binding.textViewFeeAmount.setText(strFeeAmount);
        if (amount == 0) {
            binding.editText.setHint(strAmount);
        }
        else {
            binding.editText.setText(strAmount);
        }
    }

    private void updateAmountUI() {
        defineStringAmount();
        binding.editText.setText(strAmount);
        binding.textViewFeeAmount.setText(strFeeAmount);
        binding.editText.getBackground().setColorFilter(getResources()
                .getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        binding.continueButton.setBackgroundResource(R.drawable.button_enabled_rounded_corners);
    }

    @SuppressLint("DefaultLocale")
    private void defineStringAmount() {
        strAmount = CurrentCurrency.currentCurrency.getSymbol()
                + String.format("%.2f", amount);

        float feeAmount = fee * amount;
        strFeeAmount = CurrentCurrency.currentCurrency.getSymbol()
                + String.format("%.2f", feeAmount);
    }

    private void setDefaultCurrency() {
        if (CurrentCurrency.currentCurrency == null){
            CurrentCurrency.currentCurrency = new UnitedStatesDollar();
            binding.editText.setHint(CurrentCurrency.currentCurrency.getSymbol() + "0.00");
        }
    }

    private void setCurrencyChoice() {
        binding.cardViewCurrency.setOnClickListener( view -> {
            BottomSheet bottomSheet = new BottomSheet();
            bottomSheet.show(getSupportFragmentManager(),bottomSheet.getTag());
        });
    }

    private void setAmountChoice() {
        binding.editText.setFocusable(false);
        binding.editText.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, AmountChoiceActivity.class);
            startActivityForResult(intent, requiredRequestCode);
        });
    }


}