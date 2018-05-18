package com.test.poker.action;

public abstract class Action {
    private boolean isDone = false;
    private Action action;

    public void whenDone(Action action) {
        this.action = action;
    }

    public abstract void run();

    public void done() {
        if (isDone) return;

        if (this.action != null) this.action.run();
        
        isDone = true;
    }
}
