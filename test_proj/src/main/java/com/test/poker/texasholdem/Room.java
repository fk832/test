package com.test.poker.texasholdem;

import com.test.msg.Subscription;
import com.test.poker.event.player.PlayerJoinsTheTable;
import com.test.poker.event.player.PlayerJoinsTheRoom;
import com.test.poker.event.player.PlayerLeavesTheGame;
import com.test.poker.event.player.PlayerLeavesTheRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Room {
    private static final Logger log = LogManager.getLogger(Room.class);

    public String name;

    private Context ctx;
    private Table table;
    private PlayOneRound playOneRound;

    public Room(String name, int numberOfPlayers) {
        this.name = name;
        table = new Table(numberOfPlayers);
        ctx = new Context();
        ctx.table = table;
        playOneRound = new PlayOneRound(ctx);
    }

    public void open() {
        log.info("open");
        playOneRound.whenDone(playOneRound);
        playOneRound.run();
    }

    public void close() {
        log.info("closing");
        //TODO implement graceful stop
    }

    @Subscription
    public void on(PlayerJoinsTheRoom e) {
        log.info("onPlayerJoinsTheRoom");
    }

    @Subscription
    public void on(PlayerLeavesTheRoom e) {
        log.info("onPlayerLeavesTheRoom");
    }

    @Subscription
    public void on(PlayerJoinsTheTable e) {
        log.info("onPlayerJoinsTheGame");
    }

    @Subscription
    public void on(PlayerLeavesTheGame e) {
        log.info("onPlayerLeavesTheGame");
    }
}
