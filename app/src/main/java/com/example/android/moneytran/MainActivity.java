package com.example.android.moneytran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android.moneytran.currency.CurrentCurrency;
import com.example.android.moneytran.currency.UnitedStatesDollar;
import com.example.android.moneytran.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity  implements BottomSheet.onCurrencyChangedListener {
    ActivityMainBinding binding;
    private final int requestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View rootView = binding.getRoot();
        setContentView(rootView);

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

    private void setToolbar() {
        binding.toolbar.setTitle(R.string.send_money_title);
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolbar.setNavigationOnClickListener(view -> finish());
    }

    private void updateCurrencyUI() {
        String emptyAmount = CurrentCurrency.currentCurrency.getSymbol() + "0.00";
        binding.imageViewFlagCurrency.setImageResource(CurrentCurrency.currentCurrency.getFlag());
        binding.textViewCurrency.setText(CurrentCurrency.currentCurrency.getName());
        binding.textViewCurrencyBalance.setText(CurrentCurrency.currentCurrency.getBalance());
        binding.editText.setHint(emptyAmount);
        binding.textViewFeeAmount.setText(emptyAmount);
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
            startActivityForResult(intent, requestCode);
        });
    }


}