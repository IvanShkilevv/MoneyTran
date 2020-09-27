package com.example.android.moneytran.currency;

import com.example.android.moneytran.R;

public class UnitedStatesDollar extends Currency {
    private int name = R.string.usd_name;
    private int balance = R.string.usd_balance;
    private int flag = R.drawable.flag_united_states;
    private char symbol = '\u0024';

    @Override
    public int getName() {
        return name;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public int getFlag() {
        return flag;
    }

    @Override
    public char getSymbol() {
        return symbol;
    }
}
