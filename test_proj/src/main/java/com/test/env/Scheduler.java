package com.test.env;

import java.util.TreeMap;

public class Scheduler {
    private IClock clock;

    private TreeMap<Long, Runnable> schedule = new TreeMap<Long, Runnable>();

    public Scheduler(IClock clock) {
        this.clock = clock;
    }

    public void schedule(long time, Runnable action) {
        schedule.put(time, action);
    }

    public void schedule(Runnable action) {
        long now = clock.time();
        schedule(now, action);
    }

    public int poll() {
        int numOfActions = 0;
        long now = clock.time();
        while(schedule.firstKey() <= now) {
            Runnable action = schedule.pollFirstEntry().getValue();
            action.run();
            numOfActions++;
        }

        return numOfActions;
    }
}
