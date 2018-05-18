package com.test.poker.texasholdem;

import com.test.msg.Subscription;
import com.test.poker.action.Action;
import com.test.poker.event.player.PlayerJoinsTheTable;
import com.test.poker.event.player.PlayerLeavesTheTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

abstract class RoundStep extends Action {
    protected Context ctx;

    public RoundStep(Context ctx) {
        this.ctx = ctx;
    }
}

public class PlayOneRound extends Action {

    private static final Logger log = LogManager.getLogger(PlayOneRound.class);

    private Context ctx;

    WaitForPlayers waitForPlayers;
    ForceBlinds forceBlinds;
    PreFlopBetting preFlopBetting;
    FinishAndClean finishAndClean;

    public PlayOneRound(Context ctx) {
        this.ctx = ctx;
        waitForPlayers = new WaitForPlayers(ctx);
        forceBlinds = new ForceBlinds(ctx);
        preFlopBetting = new PreFlopBetting(ctx);
        finishAndClean = new FinishAndClean(ctx);
    }

    @Override
    public void run() {
        waitForPlayers.whenDone(forceBlinds);
        forceBlinds.whenDone(preFlopBetting);
        preFlopBetting.whenDone(finishAndClean);
        waitForPlayers.run();
    }

    public class WaitForPlayers extends RoundStep {
        public WaitForPlayers(Context ctx) { super(ctx); }

        private LinkedList<RoundPlayer> players = new LinkedList<>();

        private boolean check() {
            if (ctx.table.numOfPlayers >= 2) {
                log.info("WaitForPlayers done");
                ctx.msgRouter.unsubscribe(this);
                done();
                return true;
            }
            return false;
        }

        public void run() {
            log.info("WaitForPlayers");

            if(check()) return;

            ctx.msgRouter.subscribe(this);
        }

        @Subscription
        public void on(PlayerJoinsTheTable e){
            log.info("WaitForPlayers player joins the table");
            check();
        }

        @Subscription
        public void on(PlayerLeavesTheTable e){
            log.info("WaitForPlayers player leaves the table");
            check();
        }
    }

    private class ForceBlinds extends RoundStep {
        public ForceBlinds(Context ctx) { super(ctx); }

        @Override
        public void run() {
            log.info("ForceBlinds");

        }
    }

    private class PreFlopBetting extends RoundStep {
        public PreFlopBetting(Context ctx) { super(ctx); }

        @Override
        public void run() {
            log.info("PreFlopBetting");

        }
    }

    private class FinishAndClean extends RoundStep {
        public FinishAndClean(Context ctx) { super(ctx); }

        @Override
        public void run() {
            log.info("FinishAndClean");
            //TODO do clean up here
            PlayOneRound.this.done();
        }
    }

}

