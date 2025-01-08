package org.mckayERP.AdventOfCode.utilities;

/**
 * A simple logger that will only print to the console during tests.
 */
public class AOCLogger
{
    private AOCLogger() {};

    private static AOCLogger logger = new AOCLogger();
    private static boolean isATest;

    public static AOCLogger get() {
        isATest = isJUnitTest();
        return logger;
    }

    public void logln(String msg) {
        if(isATest)
            System.out.println(msg);
    }
    public void log(String msg) {
        if(isATest)
            System.out.print(msg);
    }

    public static boolean isJUnitTest() {
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            if (element.getClassName().startsWith("org.junit.")) {
                return true;
            }
        }
        return false;
    }


}
