package com.example.android.moneytran;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.android.moneytran.currency.CurrentCurrency;
import com.example.android.moneytran.currency.UnitedStatesDollar;
import com.example.android.moneytran.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity  implements BottomSheet.onCurrencyChangedListener {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View rootView = binding.getRoot();
        setContentView(rootView);

        binding.cardViewCurrency.setOnClickListener( view -> {
            BottomSheet bottomSheet = new BottomSheet();
            bottomSheet.show(getSupportFragmentManager(),bottomSheet.getTag());
        });

        setToolbar();
        setDefaultCurrency();

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
        binding.imageViewFlagCurrency.setImageResource(CurrentCurrency.currentCurrency.getFlag());
        binding.textViewCurrency.setText(CurrentCurrency.currentCurrency.getName());
        binding.textViewCurrencyBalance.setText(CurrentCurrency.currentCurrency.getBalance());
    }

    private void setDefaultCurrency() {
        if (CurrentCurrency.currentCurrency == null){
            CurrentCurrency.currentCurrency = new UnitedStatesDollar();
        }
    }


}