package com.test.poker;

import org.junit.Test;

public class GameManagerTest {
    

    @Test
    public void InitTest() {
        GameManager gm = new GameManager();
        gm.onEvent();
    }
}
