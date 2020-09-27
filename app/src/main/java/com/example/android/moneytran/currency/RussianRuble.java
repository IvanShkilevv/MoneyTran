package com.example.android.moneytran.currency;

import com.example.android.moneytran.R;

public class RussianRuble extends Currency {
    private int name = R.string.rub_name;
    private int balance = R.string.rub_balance;
    private int flag = R.drawable.flag_russia;
    private char symbol = '\u20BD';

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
