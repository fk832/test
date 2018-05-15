package com.test.poker;

import com.test.msg.Subscription;
import com.test.poker.event.player.PlayerJoinsTheGame;
import com.test.poker.event.player.PlayerJoinsTheRoom;
import com.test.poker.event.player.PlayerLeavesTheGame;
import com.test.poker.event.player.PlayerLeavesTheRoom;
import com.test.poker.texasholdem.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameManager {
    private static final Logger log = LogManager.getLogger(GameManager.class);

    private Table table;

    public GameManager(int numberOfPlayers) {
        table = new Table(numberOfPlayers);
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
    public void on(PlayerJoinsTheGame e) {
        log.info("onPlayerJoinsTheGame");
    }

    @Subscription
    public void on(PlayerLeavesTheGame e) {
        log.info("onPlayerLeavesTheGame");
    }
}
