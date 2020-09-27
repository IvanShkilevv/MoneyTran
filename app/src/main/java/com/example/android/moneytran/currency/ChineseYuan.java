package com.example.android.moneytran.currency;

import com.example.android.moneytran.R;

public class ChineseYuan extends Currency {
    private int name = R.string.cny_name;
    private int balance = R.string.cny_balance;
    private int flag = R.drawable.flag_china;
    private char symbol = '\u5143';

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
