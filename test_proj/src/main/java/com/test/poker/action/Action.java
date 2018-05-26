package com.test.poker.action;

public abstract class Action implements Runnable {
    private Runnable runnable;

    public void whenDone(Runnable runnable) {
        this.runnable = runnable;
    }

    public abstract void run();

    public void done() {
        if (this.runnable != null) this.runnable.run();
    }
}
