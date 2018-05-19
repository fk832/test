package com.test.poker.event.player;

import com.test.msg.SequencedMsg;
import com.test.poker.Player;

public class PlayerLeavesTheTable extends SequencedMsg {
    public Player player;
}
