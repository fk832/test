package com.test.poker.action;

public class Action {
    private boolean isDone = false;
    private Runnable action;

    public void whenDone(Runnable action) {
        this.action = action;
    }

    public void done() {
        if (isDone) return;

        this.action.run();
        isDone = true;
    }
}
