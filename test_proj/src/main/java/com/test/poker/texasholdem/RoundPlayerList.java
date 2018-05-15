package com.test.poker.texasholdem;

import com.test.poker.Player;
import com.test.poker.action.Action;

import java.util.LinkedList;

public class RoundPlayerList extends Action {
    private LinkedList<RoundPlayer> players = new LinkedList<>();
    private Context ctx;

    public RoundPlayerList(Context ctx) {
        this.ctx = ctx;
    }

    public void waitForPlayers() {
        if (ctx.table.numOfPlayers >= 2) {
            done();
            return;
        }
    }
}
