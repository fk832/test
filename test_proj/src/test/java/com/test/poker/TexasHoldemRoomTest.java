package com.test.poker;

import com.test.msg.MsgRouter;
import com.test.poker.texasholdem.Room;
import org.junit.Test;

public class TexasHoldemRoomTest {
    @Test
    public void OpenCloseTest() {
        MsgRouter router = new MsgRouter();
        TestEnv env = new TestEnv(router);

        Player john = new Player(1, "John", 1500);
        Player larry = new Player(2, "Larry", 2000);

        Room room = new Room("TexasHoldem", 6, router);
        room.open();

        env.joinsTheRoom(john);
        env.joinsTheTable(john, 0);

        env.joinsTheRoom(larry);
        env.joinsTheTable(larry, 1);

        env.calls(larry);
        env.calls(john);
        
        room.close();
    }
}
