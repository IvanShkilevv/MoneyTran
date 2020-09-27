package com.example.android.moneytran.currency;

import com.example.android.moneytran.R;

public class BritishPound extends Currency {
    private int name = R.string.gbp_name;
    private int balance = R.string.gbp_balance;
    private int flag = R.drawable.flag_united_kingdom;
    private char symbol = '\u00A3';

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
