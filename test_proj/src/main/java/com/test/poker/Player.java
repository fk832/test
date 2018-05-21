package com.test.poker;

import com.test.msg.MsgRouter;

public class Player {
    public MsgRouter events = new MsgRouter();

    public int id;
    public String name;
    public double cash;
}
