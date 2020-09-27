package com.example.android.moneytran.currency;

import com.example.android.moneytran.R;

public class Euro extends Currency {
    private int name = R.string.euro_name;
    private int balance = R.string.euro_balance;
    private int flag = R.drawable.flag_european_union;
    private char symbol = '\u20AC';

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
