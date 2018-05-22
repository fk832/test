package com.test.poker.texasholdem;

import com.test.event.TimerEvent;
import com.test.msg.Subscription;
import com.test.poker.Player;
import com.test.poker.action.Action;
import com.test.poker.event.player.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class PlayOneRound extends Action {
    private static final Logger log = LogManager.getLogger(PlayOneRound.class);

    private Context ctx;

    WaitForPlayersToJoin waitForPlayersToJoin = new WaitForPlayersToJoin();
    
    PreFlop preFlop = new PreFlop();
    DividePot dividePot = new DividePot();

    public PlayOneRound(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void run() {
        waitForPlayersToJoin.whenDone(preFlop);
        preFlop.whenDone(dividePot);
        waitForPlayersToJoin.run();
    }

    public class WaitForPlayersToJoin extends Action {

        private boolean check() {
            if (ctx.table.numOfPlayers >= 2) {
                log.info("WaitForPlayers done");
                ctx.table.events.unsubscribe(this);
                ctx.roundPlayers.init(ctx.table);

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

    public class Betting extends Action {


        @Override
        public void run() {
            
        }

        public class NextPlayerAction extends Action {
            private WaitForPlayerAction waitForPlayerAction = new WaitForPlayerAction();

            public Player player;
            public Player lastPlayerToAct;
            public Context ctx;

            @Override
            public void run() {
                player = ctx.roundPlayers.next();

                if (player == lastPlayerToAct) {
                    log.info("All players acted");
                    Betting.this.done();
                    return;
                }

                waitForPlayerAction.whenDone(NextPlayerAction.this);
                waitForPlayerAction.player = player;
                waitForPlayerAction.run();
            }
        }
    }


    public class WaitForPlayerAction extends Action {
        public Player player;

        @Override
        public void run() {
            log.info("WaitForPlayerAction player [{}] id[{}]", player.name, player.id);
            player.events.subscribe(this);
        }

        @Subscription
        public void on(PlayerCalls e) {

        }

        @Subscription
        public void on(PlayerRaises e) {

        }

        @Subscription
        public void on(PlayerFolds e) {

        }

        @Subscription
        public void on(TimerEvent e) {

        }
    }

    private class PreFlop extends Action {
        private Betting betting = new Betting();

        @Override
        public void run() {
            log.info("ForceBlinds");
            log.info("PreFlopDealt");
            
            betting.whenDone(() -> { PreFlop.this.done(); } );
            betting.run();
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

