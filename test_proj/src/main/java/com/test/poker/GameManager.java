package com.test.poker;

import com.test.msg.Subscription;
import com.test.poker.event.player.PlayerJoinsTheGame;
import com.test.poker.event.player.PlayerJoinsTheRoom;
import com.test.poker.event.player.PlayerLeavesTheGame;
import com.test.poker.event.player.PlayerLeavesTheRoom;
import com.test.poker.texasholdem.Context;
import com.test.poker.texasholdem.PlayOneRound;
import com.test.poker.texasholdem.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameManager {
    private static final Logger log = LogManager.getLogger(GameManager.class);

    private Context ctx;
    private Table table;
    private PlayOneRound playOneRound;

    public GameManager(int numberOfPlayers) {
        table = new Table(numberOfPlayers);
        ctx = new Context();
        ctx.table = table;
        playOneRound = new PlayOneRound(ctx);
    }

    public void start() {
        log.info("start");
        playOneRound.whenDone(playOneRound);
        playOneRound.run();
    }

    public void stop() {
        log.info("stop");
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
    public void on(PlayerJoinsTheGame e) {
        log.info("onPlayerJoinsTheGame");
    }

    @Subscription
    public void on(PlayerLeavesTheGame e) {
        log.info("onPlayerLeavesTheGame");
    }
}
