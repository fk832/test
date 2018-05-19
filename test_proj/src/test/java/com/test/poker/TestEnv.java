package com.test.poker;

import com.test.msg.MsgRouter;
import com.test.poker.event.player.PlayerJoinsTheTable;
import com.test.poker.event.player.PlayerJoinsTheRoom;

public class TestEnv {
    private PlayerJoinsTheRoom playerJoinsTheRoom = new PlayerJoinsTheRoom();
    private PlayerJoinsTheTable playerJoinsTheTable = new PlayerJoinsTheTable();

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
}
