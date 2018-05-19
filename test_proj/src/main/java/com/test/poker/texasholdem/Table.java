package com.test.poker.texasholdem;

import com.test.msg.MsgRouter;
import com.test.msg.Subscription;
import com.test.poker.Player;
import com.test.poker.event.player.PlayerJoinsTheTable;
import com.test.poker.event.player.PlayerLeavesTheTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Table {
    private static final Logger log = LogManager.getLogger(Table.class);

    private Player[] seats;

    public int buttonIndex;
    public int numOfPlayers;

    public MsgRouter events = new MsgRouter();

    public Table(int numberOfSeats) {
        seats = new Player[numberOfSeats];
    }

    @Subscription
    public void on(PlayerJoinsTheTable e){
        if (seats[e.seatIdx] != null){
            log.error("player [{}] tried to join the table but seatIdx[{}] is busy", e.player.name, e.seatIdx);
            return;
        }

        log.info("player [{}] joins the table seatIdx[{}]", e.player.name, e.seatIdx);
        seats[e.seatIdx] = e.player;
        numOfPlayers++;
        events.route(e);
    }

    @Subscription
    public void on(PlayerLeavesTheTable e){
        for (int i = 0; i < seats.length; i++) {
            if (e.player == seats[i]) {
                log.info("player [{}] leaves the table seatIdx[{}]", e.player, i);
                seats[i] = null;
                numOfPlayers--;
                events.route(e);
            }
        }

        log.error("player [{}] tried to leave the table but wasn't there", e.player);
    }
}
