package net.openhft.exceptionhandler;

import static java.lang.management.ManagementFactory.getRuntimeMXBean;

/**
 * Created by Peter on 07/04/2016.
 */
public class DebugExceptionHandler implements ExceptionHandler {
    private static final boolean IS_DEBUG = getRuntimeMXBean().getInputArguments().toString().contains("jdwp") || Boolean.getBoolean("debug");

    final ExceptionHandler run, debug;

    public DebugExceptionHandler(ExceptionHandler run, ExceptionHandler debug) {
        this.run = run;
        this.debug = debug;
    }

    public void onException(String message, Throwable t) {
        if (IS_DEBUG)
            debug.onException(message, t);
        else
            run.onException(message, t);
    }
}
