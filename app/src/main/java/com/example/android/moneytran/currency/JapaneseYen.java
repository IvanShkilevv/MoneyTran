package com.example.android.moneytran.currency;

import com.example.android.moneytran.R;

public class JapaneseYen extends Currency {
    private int name = R.string.jpy_name;
    private int balance = R.string.jpy_balance;
    private int flag = R.drawable.flag_japan;
    private char symbol = '\u00A5';

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
