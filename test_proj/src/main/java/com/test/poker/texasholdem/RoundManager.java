package com.test.poker.texasholdem;

import com.test.poker.event.round.RoundStarted;

public class RoundManager {
    private Context ctx;

    public RoundManager(Context ctx) {
        this.ctx = ctx;
    }

    public void waitForPlayers() {
        RoundPlayerList list = new RoundPlayerList(ctx);
        list.whenDone(this::startRound);
        list.waitForPlayers();
    }

    public void startRound() {
        
    }

    public void on(RoundStarted e) {

    }
}
