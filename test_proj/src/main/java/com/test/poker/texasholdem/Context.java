package com.test.poker.texasholdem;

import com.test.env.Scheduler;
import com.test.msg.MsgRouter;

public class Context {
    public Scheduler scheduler;
    public MsgRouter msgRouter;
    public Table table;
    public RoundPlayers roundPlayers;
    public Deck deck;
}
