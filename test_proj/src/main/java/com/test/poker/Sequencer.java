package com.test.poker;

public class Sequencer {
    private int seqId = 0;

    private EventStore store;
    private EventPublisher publisher;

    public void onCommand(Event command) {
        seqId++;
        command.setSeqId(seqId);
        store.save(command);
        publisher.publish(command);
    }
}
