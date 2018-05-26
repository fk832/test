package com.test.poker.texasholdem;

import com.test.poker.Player;

import java.util.ArrayList;
import java.util.List;

public class RoundPlayers {
    public List<Player> players = new ArrayList<>();
    public int curIdxToAct;
    public int lastIdxToAct;

    public Player curPlayerToAct() {
        return players.get(curIdxToAct);
    }

    public void init(Table table) {
        players.clear();

        int startIndex = table.buttonIndex;
        int size = table.seats.length;
        for (int i = 0; i < table.seats.length; i++) {
            int j = (i + startIndex) % size;
            if (table.seats[j] == null) continue;
            players.add(table.seats[j]);
        }
        //curIdxToAct = 2 % players.size();
        curIdxToAct = -1;
        lastIdxToAct = 1;

    }

    public boolean moveToNextIdxToAct() {
        if (curIdxToAct < 0) {
            curIdxToAct = 2 % players.size();
            return true;
        }

        if (curIdxToAct == lastIdxToAct) return false;

        curIdxToAct = (curIdxToAct + 1) % players.size();
        return true;
    }

    public void remove(Player player) {
        players.remove(player);
    }
}
