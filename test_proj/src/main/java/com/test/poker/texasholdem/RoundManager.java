package com.test.poker.texasholdem;

import com.test.env.Scheduler;
import com.test.msg.MsgRouter;
import com.test.poker.event.round.RoundStarted;

public class RoundManager {
    private Context ctx;


    public RoundManager(Context ctx) {
        this.ctx = ctx;
    }

    public void start() {

        dealPreFlop();

    }

    public void dealPreFlop() {
        
    }

    public void on(RoundStarted e) {

    }
}
