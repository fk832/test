package com.test.poker.texasholdem;

import com.test.poker.Player;
import com.test.poker.action.Action;

import java.util.LinkedList;

public class RoundPlayerListReady extends Action {
    private LinkedList<RoundPlayer> players = new LinkedList<>();

    public void start(Context ctx) {
        if (ctx.table.numOfPlayers >= 2) {
            done();
            return;
        }


    }
}
