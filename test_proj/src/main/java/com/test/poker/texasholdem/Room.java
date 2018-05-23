package com.test.poker.texasholdem;

import com.test.msg.MsgRouter;
import com.test.msg.Subscription;
import com.test.poker.event.player.PlayerJoinsTheTable;
import com.test.poker.event.player.PlayerJoinsTheRoom;
import com.test.poker.event.player.PlayerLeavesTheTable;
import com.test.poker.event.player.PlayerLeavesTheRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Room {
    private static final Logger log = LogManager.getLogger(Room.class);

    public String name;

    private Context ctx;
    private Round round;

    public Room(String name, int numberOfPlayers, MsgRouter msgRouter) {
        this.name = name;
        ctx = new Context();
        ctx.table = new Table(numberOfPlayers);
        ctx.msgRouter = msgRouter;
        ctx.playerActionMsgRouter = new PlayerActionMsgRouter();
        ctx.roundPlayers = new RoundPlayers();
        ctx.msgRouter.subscribe(ctx.table);
        ctx.msgRouter.subscribe(ctx.playerActionMsgRouter);
        round = new Round(ctx);
    }

    public void open() {
        log.info("open");
        round.whenDone(round);
        round.run();
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
    public void on(PlayerLeavesTheTable e) {
        log.info("onPlayerLeavesTheGame");
    }
}
