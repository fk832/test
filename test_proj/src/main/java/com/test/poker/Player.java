package com.test.poker;

import com.test.msg.MsgRouter;

public class Player {
    public MsgRouter events = new MsgRouter();

    public int id;
    public String name;
    public double cash;

    public Player(int id, String name, double cash) {
        this.id = id;
        this.name = name;
        this.cash = cash;
    }
}
