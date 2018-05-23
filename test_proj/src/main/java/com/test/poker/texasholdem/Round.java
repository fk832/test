package com.test.poker.texasholdem;

import com.test.event.TimerEvent;
import com.test.msg.Subscription;
import com.test.poker.Player;
import com.test.poker.action.Action;
import com.test.poker.event.player.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Round extends Action {
    private static final Logger log = LogManager.getLogger(Round.class);

    private Context ctx;

    CollectRoundPlayers collectRoundPlayers = new CollectRoundPlayers();
    PreFlop preFlop = new PreFlop();
    DividePot dividePot = new DividePot();

    public Round(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void run() {
        collectRoundPlayers.whenDone(preFlop);
        preFlop.whenDone(dividePot);
        collectRoundPlayers.run();
    }

    public class CollectRoundPlayers extends Action {

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
        public NextPlayerAction nextPlayerAction = new NextPlayerAction();

        @Override
        public void run() {
            log.info("Betting started");
            nextPlayerAction.run();
        }

        public class NextPlayerAction extends Action {
            private PlayerAction playerAction = new PlayerAction();

            @Override
            public void run() {
                if (!ctx.roundPlayers.moveToNextIdxToAct()) {
                    log.info("NextPlayerAction all players acted");
                    Betting.this.done();
                    return;
                }
                log.info("NextPlayerAction idx[{}]", ctx.roundPlayers.curIdxToAct);
                playerAction.whenDone(NextPlayerAction.this);
                playerAction.run();
            }
        }
    }

    public class PlayerAction extends Action {
        public Player p;
        @Override
        public void run() {
            p = ctx.roundPlayers.curPlayerToAct();
            log.info("PlayerAction waiting for [{}] id[{}]", p.name, p.id);
            ctx.playerActionMsgRouter.subscribe(p);
            p.events.subscribe(this);
        }

        @Subscription
        public void on(PlayerCalls e) {
            log.info("PlayerAction [{}] calls", p.name);
        }

        @Subscription
        public void on(PlayerRaises e) {
            log.info("PlayerAction [{}] raises", p.name);
        }

        @Subscription
        public void on(PlayerFolds e) {
            log.info("PlayerAction [{}] folds", p.name);
        }

        @Subscription
        public void on(TimerEvent e) {
            log.info("PlayerAction [{}] timeouts", p.name);
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
            Round.this.done();
        }
    }
}

