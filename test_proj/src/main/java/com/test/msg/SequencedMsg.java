package com.test.msg;

public class SequencedMsg {

    public enum Type { Command, Event, Reject }

    public long seqId;
    public Type type;
}
