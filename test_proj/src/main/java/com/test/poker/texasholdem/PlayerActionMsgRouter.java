package com.test.poker.texasholdem;

import com.test.msg.MsgRouter;
import com.test.msg.Subscription;
import com.test.poker.Player;
import com.test.poker.event.player.PlayerCalls;
import com.test.poker.event.player.PlayerFolds;
import com.test.poker.event.player.PlayerRaises;

import java.util.HashMap;
import java.util.Map;

public class PlayerActionMsgRouter {
    private Map<Integer, Player> subs = new HashMap<>();

    private void route(int id, Object e) {
        Player p = subs.get(id);
        if (p == null) return;
        p.events.route(e);
    }

    public void subscribe(Player p) {
        subs.put(p.id, p);
    }

    public void unsubscribe(Player p) {
        subs.remove(p.id);
    }

    @Subscription
    public void on(PlayerCalls e) {
        route(e.player.id, e);
    }

    @Subscription
    public void on(PlayerFolds e) {
        route(e.player.id, e);
    }

    @Subscription
    public void on(PlayerRaises e) {
        route(e.player.id, e);
    }

}
