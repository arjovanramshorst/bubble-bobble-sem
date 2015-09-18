package sem.group15.bubblebobble.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoggerTest {

    /**
     * Tests if there will be only one instance of a logger from a certain class.
     */
    @Test
    public void getLoggerTest() {
        Logger logger1 = Logger.getLogger(LoggerTest.class.getName());
        Logger logger2 = Logger.getLogger(LoggerTest.class.getName());
        assertTrue(logger1 == logger2);
    }

    /**
     * Tests if there are different instances of loggers when they have different names.
     */
    @Test
    public void getDifferentLoggerTest() {
        Logger logger1 = Logger.getLogger("1");
        Logger logger2 = Logger.getLogger("2");
        assertTrue(logger1 != logger2);
    }

}