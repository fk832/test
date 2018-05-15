package com.test.poker;

import com.test.msg.MsgRouter;
import com.test.poker.event.player.PlayerJoinsTheGame;
import com.test.poker.event.player.PlayerJoinsTheRoom;
import org.junit.Test;

public class GameManagerTest {
    @Test
    public void InitTest() {
        MsgRouter bus = new MsgRouter();
        GameManager gameManager = new GameManager();
        bus.subscribe(gameManager);

        PlayerJoinsTheRoom playerJoinsTheRoom = new PlayerJoinsTheRoom();
        bus.route(playerJoinsTheRoom);

        PlayerJoinsTheGame playerSitsTheTable = new PlayerJoinsTheGame();
        bus.route(playerSitsTheTable);
        

    }
}
