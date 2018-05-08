package com.test.poker;

import com.test.poker.event.PlayerJoined;
import com.test.poker.event.PlayerRaised;
import com.test.poker.event.SequencedEvent;
import com.test.poker.event.Subscription;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameManager {
    private static final Logger log = LogManager.getLogger(GameManager.class);
    
    @Subscription
    public void onPlayerJoined(PlayerJoined e) {
        log.info("onPlayerJoined");
    }

    @Subscription
    public void onPlayerRaised(PlayerRaised e) {
        log.info("onPlayerRaised");
    }

    @Subscription
    public void onSequencedEvent(SequencedEvent e) {
        log.info("onSequencedEvent");
    }
}
