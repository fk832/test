package com.test.poker.texasholdem;

import com.test.msg.Subscription;
import com.test.poker.action.Action;
import com.test.poker.event.player.PlayerJoinsTheTable;
import com.test.poker.event.player.PlayerLeavesTheTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class PlayOneRound extends Action {
    private static final Logger log = LogManager.getLogger(PlayOneRound.class);

    private Context ctx;

    WaitForPlayers waitForPlayers = new WaitForPlayers();
    PreFlopBetting preFlopBetting = new PreFlopBetting();;
    DividePot dividePot = new DividePot();;

    public PlayOneRound(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void run() {
        waitForPlayers.whenDone(preFlopBetting);
        preFlopBetting.whenDone(dividePot);
        waitForPlayers.run();
    }

    public class WaitForPlayers extends Action {
        private LinkedList<RoundPlayer> players = new LinkedList<>();

        private boolean check() {
            if (ctx.table.numOfPlayers >= 2) {
                log.info("WaitForPlayers done");
                ctx.table.events.unsubscribe(this);
                done();
                return true;
            }
            return false;
        }

        public void run() {
            log.info("WaitForPlayers waiting");

            if(check()) return;

            ctx.table.events.subscribe(this);
        }

        @Subscription
        public void on(PlayerJoinsTheTable e){
            log.info("WaitForPlayers player [{}] joins the table", e.player.name);
            check();
        }

        @Subscription
        public void on(PlayerLeavesTheTable e){
            log.info("WaitForPlayers player [{}] leaves the table", e.player.name);
            check();
        }
    }

    private class PreFlopBetting extends Action {
        @Override
        public void run() {
            log.info("ForceBlinds");
            log.info("PreFlopBetting");
            done();
        }
    }

    private class DividePot extends Action {
        @Override
        public void run() {
            log.info("DividePot");
            //TODO do clean up here
            PlayOneRound.this.done();
        }
    }
}

