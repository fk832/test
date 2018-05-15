package com.test.poker.texasholdem;

import com.test.poker.Player;

public class Table {
    private Player[] seats;

    public int buttonIndex;
    public int numOfPlayers;

    public Table(int numberOfSeats) {
        seats = new Player[numberOfSeats];
    }

    public boolean sitDown(Player player, int seatIdx) {
        if (seats[seatIdx] != null) return false;

        seats[seatIdx] = player;
        numOfPlayers++;
        return true;
    }

    public boolean standUp(Player player) {
        for (int i = 0; i < seats.length; i++) {
            if (player == seats[i]) {
                seats[i] = null;
                numOfPlayers--;
                return true;
            }
        }

        return false;
    }
}
