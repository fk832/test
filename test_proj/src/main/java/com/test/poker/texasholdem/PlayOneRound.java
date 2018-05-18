package com.test.poker.texasholdem;

import com.test.poker.action.Action;
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

    private class WaitForPlayers extends RoundStep {
        public WaitForPlayers(Context ctx) { super(ctx); }

        private LinkedList<RoundPlayer> players = new LinkedList<>();

        public void run() {
            log.info("WaitForPlayers");
            if (ctx.table.numOfPlayers >= 2) {
                done();
                return;
            }

            //TODO subscribe for players table events
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

