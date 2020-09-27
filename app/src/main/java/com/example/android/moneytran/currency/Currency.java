package com.example.android.moneytran.currency;

import android.widget.ImageView;

public abstract class Currency {
    private int name;
    private int balance;
    private int flag;
    private char symbol;

    public abstract int getName();

    public abstract int getBalance();

    public abstract int getFlag();

    public abstract char getSymbol();
}
