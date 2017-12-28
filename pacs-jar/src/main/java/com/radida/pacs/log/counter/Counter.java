package com.radida.pacs.log.counter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author baixd
 */
public class Counter {
    private static AtomicLong All_COUNTER = new AtomicLong(0);

    public static void add() {
        All_COUNTER.getAndIncrement();
    }

    public static void subtract(int l) {
        All_COUNTER.addAndGet(l * -1);
    }

    public static long get() {
        return All_COUNTER.get();
    }
}
