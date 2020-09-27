package com.example.android.moneytran.currency;

import com.example.android.moneytran.R;

public class UkrainianHryvnia extends Currency {
    private int name = R.string.uah_name;
    private int balance = R.string.uah_balance;
    private int flag = R.drawable.flag_ukraine;
    private char symbol = '\u20B4';

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
