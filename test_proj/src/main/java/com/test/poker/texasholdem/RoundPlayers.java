package com.test.poker.texasholdem;

import com.test.poker.Player;

import java.util.ArrayList;
import java.util.List;

public class RoundPlayers {
    public List<Player> players = new ArrayList<>();

    public void init(Table table) {
        players.clear();

        int startIndex = table.buttonIndex;
        int size = table.seats.length;

        for (int i = 0; i < table.seats.length; i++) {
            int j = (i + startIndex) % size;
            if (table.seats[j] == null) continue;
            players.add(table.seats[j]);
        }
    }

    public void remove(Player player) {
        players.remove(player);
    }
}
