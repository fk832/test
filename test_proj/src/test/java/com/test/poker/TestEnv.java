package com.test.poker;

import com.test.msg.MsgRouter;
import com.test.poker.event.player.*;

public class TestEnv {
    private PlayerJoinsTheRoom playerJoinsTheRoom = new PlayerJoinsTheRoom();
    private PlayerJoinsTheTable playerJoinsTheTable = new PlayerJoinsTheTable();
    private PlayerCalls playerCalls = new PlayerCalls();
    private PlayerFolds playerFolds = new PlayerFolds();
    private PlayerRaises playerRaises = new PlayerRaises();

    private  MsgRouter router;

    public TestEnv(MsgRouter router) {
        this.router = router;
    }

    public void joinsTheRoom(Player p) {
        playerJoinsTheRoom.player = p;
        router.route(playerJoinsTheRoom);
    }

    public void joinsTheTable(Player p, int seatIdx) {
        playerJoinsTheTable.player = p;
        playerJoinsTheTable.seatIdx = seatIdx;
        router.route(playerJoinsTheTable);
    }

    public void calls(Player p) {
        playerCalls.player = p;
        router.route(playerCalls);
    }

    public void folds(Player p) {
        playerFolds.player = p;
        router.route(playerFolds);
    }

    public void raises(Player p) {
        playerRaises.player = p;
        router.route(playerRaises);
    }
}
