package util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * To generate a fake database ID.
 * 
 * @author GuoLin
 *
 */
public class IdGenerator {

    private static final AtomicInteger SEQUENCE = new AtomicInteger();

    public static Integer generate() {
        return SEQUENCE.getAndIncrement();
    }

}
