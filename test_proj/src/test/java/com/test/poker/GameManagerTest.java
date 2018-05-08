package com.test.poker;

import com.test.poker.event.PlayerJoined;
import com.test.poker.event.PlayerRaised;
import org.junit.Test;

public class GameManagerTest {
    @Test
    public void InitTest() {
        MsgBus bus = new MsgBus();
        GameManager gameManager = new GameManager();
        bus.subscribe(gameManager);

        PlayerJoined playerJoined = new PlayerJoined();
        bus.route(playerJoined);

        PlayerRaised playerRaised = new PlayerRaised();
        bus.route(playerRaised);
    }
}
