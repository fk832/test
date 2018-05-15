package com.test.env;

public class TestClock implements IClock {
    private long currentTime;

    public void time(long currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public long time() {
        return currentTime;
    }
}
