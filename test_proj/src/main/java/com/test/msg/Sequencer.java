package com.test.msg;

public class Sequencer {
    private int seqId = 0;

    private MsgStore store;

    public void on(SequencedMsg command) {
        seqId++;
        command.seqId = seqId;
        store.save(command);
    }
}
