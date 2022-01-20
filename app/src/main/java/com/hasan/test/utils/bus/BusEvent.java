package com.hasan.test.utils.bus;

public abstract class BusEvent {

    public final Object eventTrigger;

    public BusEvent(Object eventTrigger) {
        this.eventTrigger = eventTrigger;
    }
}
