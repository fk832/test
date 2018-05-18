package com.test.poker;

import com.test.msg.MsgRouter;
import com.test.poker.texasholdem.Room;
import org.junit.Test;

public class TexasHoldemRoomTest {
    @Test
    public void OpenCloseTest() {
        MsgRouter router = new MsgRouter();
        TestEnv env = new TestEnv(router);

        Player john = new Player();
        Player larry = new Player();
        env.add(john);
        env.add(larry);

        Room room = new Room("TexasHoldem", 6);
        room.open();

        env.joinsTheRoom(john);
        env.joinsTheTable(john);

        env.joinsTheRoom(larry);
        env.joinsTheTable(larry);

        room.close();
    }
}
